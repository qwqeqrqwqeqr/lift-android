package com.gradation.lift.feature.my_info.my_info.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignOutUseCase
import com.gradation.lift.domain.usecase.badge.GetUserBadgeUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.my_info.my_info.data.state.SignOutState
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [MyInfoMyInfoViewModel]
 * @property badgeCount 사용자가 획득한 뱃지 개수
 * @property workCount 사용자의 운동 총 횟수
 * @property userDetailUiState 사용자 상세정보 상태
 * @property signOutState 사용자의 로그아웃을 관측하기 위한 상태
 * @since 2023-09-01 14:12:22s
 */
@HiltViewModel
class MyInfoMyInfoViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    getHistoryUseCase: GetHistoryUseCase,
    private val getUserBadgeUseCase: GetUserBadgeUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {


    internal val badgeCount: StateFlow<Int> = getUserBadgeUseCase().map {
        when (it) {
            is DataState.Fail -> 0
            is DataState.Success -> it.data.count()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )

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

    internal val signOutState: MutableStateFlow<SignOutState> =
        MutableStateFlow(SignOutState.None)


    internal fun updateUpdateUserDetailState(): (SignOutState) -> Unit = {
        signOutState.value = it
    }

    fun signOut(): ()-> Unit = {
        viewModelScope.launch {
            signOutUseCase().collect {
                when (it) {
                    is DataState.Fail -> {
                        signOutState.value =SignOutState.Fail("로그아웃을 실패하였습니다.")
                    }
                    is DataState.Success -> {
                        signOutState.value = SignOutState.Success
                    }
                }
            }
        }
    }


}