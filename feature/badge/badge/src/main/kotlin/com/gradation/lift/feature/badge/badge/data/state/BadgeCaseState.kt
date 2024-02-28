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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @property mainFlagBadgeChangeSet 변경사항을 담는 집합
 * @property mainFlagBadgeSet 대표 플래그로 설정된 뱃지들에 대한 집합
 * @property searchText 검색어
 * @property userBadgeList 사용자 뱃지 리스트
 * @since 2024-02-27 21:21:43
 */
class BadgeCaseState(
    updateUserBadgeMainFlagUseCase: UpdateUserBadgeMainFlagUseCase,
    badgeUiState: StateFlow<BadgeUiState>,
    viewModelScope: CoroutineScope,
    val badgeCaseSnackbarState: MutableStateFlow<BadgeCaseSnackbarState> = MutableStateFlow(
        BadgeCaseSnackbarState.None
    ),
    val updateBadgeCaseSnackbarState: (BadgeCaseSnackbarState) -> Unit = {
        badgeCaseSnackbarState.value = it
    },
    private val mainFlagBadgeChangeSet: MutableStateFlow<Set<Pair<Int, Boolean>>> =
        MutableStateFlow(
            emptySet()
        ),
    val mainFlagBadgeSet: MutableStateFlow<Set<UserBadge>> = MutableStateFlow(emptySet()),
    val searchText: MutableStateFlow<String> = MutableStateFlow(""),
    val updateSearchText: (String) -> Unit = { searchText.value = it },
    private val userBadgeList: StateFlow<List<UserBadge>> = badgeUiState.map {
        when (it) {
            is BadgeUiState.Fail -> emptyList()
            BadgeUiState.Loading -> emptyList()
            is BadgeUiState.Success -> {
                mainFlagBadgeSet.update { set -> set.plus(it.userBadgeList.filter { it.mainFlag }) }
                it.userBadgeList
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    ),
    val filteredUserBadgeList: StateFlow<List<UserBadge>> = combine(
        userBadgeList,
        searchText
    ) { badgeList, text ->
        if (text.isEmpty()) badgeList
        else {
            badgeList.filter { it.badge.name.contains(text) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    ),


    val mainFlagBadgeChangeListIsEmpty: StateFlow<Boolean> = mainFlagBadgeChangeSet.map {
        it.isEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    ),
) {
    val appendBadge: (UserBadge) -> Unit = { badge ->
        if (mainFlagBadgeSet.value.size >= 5) {
            updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.FillMaxed())
        } else {
            mainFlagBadgeSet.update { it.plus(badge) }
            mainFlagBadgeChangeSet.value =
                mainFlagBadgeChangeSet.value.filter {
                    it.first != badge.badge.id
                }.plus(badge.badge.id to true).toSet()
        }
    }

    val removeBadge: (UserBadge) -> Unit = { badge ->
        mainFlagBadgeSet.update { it.minus(badge) }
        mainFlagBadgeChangeSet.value =
            mainFlagBadgeChangeSet.value.filter {
                it.first != badge.badge.id
            }.plus(badge.badge.id to false).toSet()
    }

    val updateUserBadgeMainFlag: () -> Unit = {
        viewModelScope.launch {
            updateUserBadgeMainFlagUseCase(
                UpdateUserBadgeMainFlag(mainFlagBadgeChangeSet.value.toList())
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.Fail())
                    }

                    is DataState.Success -> {
                        mainFlagBadgeChangeSet.value = emptySet()
                        updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.UpdateCompleted())
                    }
                }
            }
        }
    }

}