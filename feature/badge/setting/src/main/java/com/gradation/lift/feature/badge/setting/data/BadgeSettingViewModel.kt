package com.gradation.lift.feature.badge.setting.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetUserBadgeByMainFlagUseCase
import com.gradation.lift.domain.usecase.badge.GetUserBadgeUseCase
import com.gradation.lift.domain.usecase.badge.UpdateUserBadgeMainFlagUseCase
import com.gradation.lift.feature.badge.setting.data.state.BadgeUiState
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [badgeUiState] 대표뱃지 설정 화면 상태
 * [updateBadgeList] 현재 업데이트 된 뱃지리스트
 * [buttonCondition] 적용하기 조건 (업데이트가 된 뱃지가 하나라도 있을 경우에 활성화)
 * @since 2023-09-30 13:55:57
 */
@HiltViewModel
class BadgeSettingViewModel @Inject constructor(
    private val getUserBadgeUseCase: GetUserBadgeUseCase,
    private val updateUserBadgeMainFlagUseCase: UpdateUserBadgeMainFlagUseCase,
) : ViewModel() {


    val badgeUiState: StateFlow<BadgeUiState> = getUserBadgeUseCase().map {
        when (it) {
            is DataState.Fail -> BadgeUiState.Fail(it.message)
            is DataState.Success -> {
                mainBadgeSet.update { set -> set.plus(it.data.filter { it.mainFlag }) }
                BadgeUiState.Success(it.data)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BadgeUiState.Loading
    )
    private val updateBadgeList: MutableStateFlow<MutableList<Pair<Int, Boolean>>> =
        MutableStateFlow(mutableListOf())
    val mainBadgeSet: MutableStateFlow<Set<UserBadge>> = MutableStateFlow(emptySet())

    val buttonCondition: StateFlow<Boolean> = updateBadgeList.map {
        it.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    fun appendBadgeInMain(): (UserBadge) -> Unit = { userBadge ->
        mainBadgeSet.update { it.plus(userBadge.copy(mainFlag = true)) }
        updateBadgeList.value = updateBadgeList.value.filter {
            it.first != userBadge.badge.id
        }.plus(Pair(userBadge.badge.id, true)).toMutableList()
    }

    fun removeBadgeInMain(): (UserBadge) -> Unit = { userBadge ->
        mainBadgeSet.update { it.minus(userBadge) }
        updateBadgeList.value = updateBadgeList.value.filter {
            it.first != userBadge.badge.id
        }.plus(Pair(userBadge.badge.id, false)).toMutableList()
    }

    fun updateUserBadgeMainFlag(): () -> Unit = {
        viewModelScope.launch {
            updateUserBadgeMainFlagUseCase(
                UpdateUserBadgeMainFlag(updateBadgeList.value)
            ).collect {
                when (it) {
                    is DataState.Fail -> {}
                    is DataState.Success -> {
                        updateBadgeList.value = mutableListOf()
                    }
                }
            }
        }
    }
}