package com.gradation.lift.feature.home.home.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetUserBadgeByMainFlagUseCase
import com.gradation.lift.domain.usecase.routine.GetMostUsedRoutineSetRoutineUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [HomeViewModel]
 * @property userDetailUiState 사용자 상세정보 상태
 * @property badgeUiState 대표 뱃지 상태
 * @property routineUiState 루틴 상태
 * @since 2024-01-07 18:40:21
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    getUserBadgeByMainFlagUseCase: GetUserBadgeByMainFlagUseCase,
    getMostUsedRoutineSetRoutineUseCase: GetMostUsedRoutineSetRoutineUseCase,
) : ViewModel() {


    internal val userDetailUiState: StateFlow<UserDetailUiState> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> UserDetailUiState.Fail(it.message)
            is DataState.Success -> UserDetailUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserDetailUiState.Loading
    )

    internal val badgeUiState: StateFlow<BadgeUiState> = getUserBadgeByMainFlagUseCase().map {
        when (it) {
            is DataState.Fail -> BadgeUiState.Fail(it.message)
            is DataState.Success -> BadgeUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BadgeUiState.Loading
    )

    internal val routineUiState: StateFlow<RoutineUiState> =
        getMostUsedRoutineSetRoutineUseCase().map {
            when (it) {
                is DataState.Fail -> RoutineUiState.Fail(it.message)
                is DataState.Success -> {
                    if (it.data.isEmpty()) RoutineUiState.Empty else RoutineUiState.Success(it.data)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineUiState.Loading
        )


}




