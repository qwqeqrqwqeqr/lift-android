package com.gradation.lift.feature.myInfo.profile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignOutUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileState
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileUiState
import com.gradation.lift.feature.myInfo.profile.data.state.SignOutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    internal val profileUiState: StateFlow<ProfileUiState> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> ProfileUiState.Fail(it.message)
            is DataState.Success -> ProfileUiState.Success(
                it.data.let { userDetail ->
                    ProfileState(
                        name = userDetail.name,
                        profilePicture = userDetail.profilePicture,
                        height = userDetail.height,
                        weight = userDetail.weight,
                        gender = userDetail.gender
                    )
                }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProfileUiState.Loading
    )


    internal val signOutState: MutableStateFlow<SignOutState> = MutableStateFlow(SignOutState.None)

    internal fun updateSignOutState(): (SignOutState) -> Unit = {
        signOutState.value = it
    }

    fun signOut(): () -> Unit = {
        viewModelScope.launch {
            signOutUseCase().collect {
                when (it) {
                    is DataState.Fail -> {
                        signOutState.value = SignOutState.Fail("로그아웃을 실패하였습니다.")
                    }

                    is DataState.Success -> {
                        signOutState.value = SignOutState.Success
                    }
                }
            }
        }
    }


}
