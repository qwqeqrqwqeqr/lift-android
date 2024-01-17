package com.gradation.lift.feature.home.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetUserBadgeByConditionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeSharedViewModel @Inject constructor(
    getUserBadgeByConditionUseCase: GetUserBadgeByConditionUseCase,
) : ViewModel() {




    val badgeConditionState: StateFlow<BadgeConditionState> =
        getUserBadgeByConditionUseCase().map { state ->
            when (state) {
                is DataState.Fail -> {
                    BadgeConditionState.None
                }

                is DataState.Success -> {
                    state.data.badge?.let { BadgeConditionState.Success(it) }
                        ?: BadgeConditionState.None
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = BadgeConditionState.None
        )

}