package com.gradation.lift.feature.badge.badge.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.UpdateUserBadgeMainFlagUseCase
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @property mainFlagBadgeChangeSet 변경사항을 담는 리스트
 * @property userBadgeList 사용자 뱃지 리스트/?
 * @property userBadgeMainFlagSet 대표 플래그로 설정된 뱃지들에 대한 집합
 * @since 2024-02-27 21:21:43
 */
class BadgeCaseState(
    updateUserBadgeMainFlagUseCase: UpdateUserBadgeMainFlagUseCase,
    badgeUiState: StateFlow<BadgeUiState>,
    viewModelScope: CoroutineScope,
    private val mainFlagBadgeChangeSet: MutableStateFlow<Set<Pair<Int, Boolean>>> = MutableStateFlow(
        emptySet()
    ),
    val userBadgeList: StateFlow<List<UserBadge>> = badgeUiState.map {
        when (it) {
            is BadgeUiState.Fail -> emptyList()
            BadgeUiState.Loading -> emptyList()
            is BadgeUiState.Success -> it.userBadgeList
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),
    private val userBadgeMainFlagSet: StateFlow<Set<Int>> = combine(
        userBadgeList,
        mainFlagBadgeChangeSet
    ) { userBadge, changeList ->

        userBadge.asSequence().filter { it.mainFlag }
            .filter { badge ->
                changeList.any {
                    badge.badge.id == it.first && it.second
                } || changeList.none {
                    badge.badge.id == it.first
                }
            }.map { it.badge.id }
            .plus(changeList.filter { it.second }.map { it.first })
            .toSet()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptySet()
    ),

    val mainFlagUserBadgeList: StateFlow<List<UserBadge>> = combine(
        userBadgeList,
        userBadgeMainFlagSet
    ) { badgeList, mainFlagSet ->
        badgeList.filter { badge -> mainFlagSet.any { it == badge.badge.id } }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),


    val mainFlagBadgeChangeListIsEmpty: StateFlow<Boolean> = combine(
        userBadgeList,
        mainFlagBadgeChangeSet
    ) { badgeList, changeList ->
        changeList.none { changeItem ->
            (badgeList.find { it.badge.id == changeItem.first }?.mainFlag == true && !changeItem.second)
                    || (badgeList.find { it.badge.id == changeItem.first }?.mainFlag == false && changeItem.second)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    ),
) {
    val appendBadge: (Int) -> Unit = { badgeId ->
        mainFlagBadgeChangeSet.value =
            mainFlagBadgeChangeSet.value.filter {
                it.first != badgeId
            }.plus(badgeId to true).toSet()
    }

    val removeBadge: (Int) -> Unit = { badgeId ->
        mainFlagBadgeChangeSet.value =
            mainFlagBadgeChangeSet.value.filter {
                it.first != badgeId
            }.plus(badgeId to false).toSet()
    }

    val updateUserBadgeMainFlag: () -> Unit = {
        viewModelScope.launch {
            updateUserBadgeMainFlagUseCase(
                UpdateUserBadgeMainFlag(mainFlagBadgeChangeSet.value.toList())
            ).collect {
                when (it) {
                    is DataState.Fail -> {}
                    is DataState.Success -> {
                        mainFlagBadgeChangeSet.value = emptySet()
                    }
                }
            }
        }
    }

}