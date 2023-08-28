package com.gradation.lift.feature.my_info.my_info.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * [MyInfoMyInfoViewModel]
 * @property workCount 사용자의 운동 총 횟수
 * @property userDetailUiState 사용자 상세정보 상태
 * @since 2023-08-25 13:33:36
 */
@HiltViewModel
class MyInfoMyInfoViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {



    internal val workCount: StateFlow<Int> = getHistoryUseCase().map {
        when (it) {
            is DataState.Fail -> 0
            is DataState.Success -> it.data.count()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

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


}