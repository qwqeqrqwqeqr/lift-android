package com.gradation.lift.feature.login.resetPassword.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.UpdateUserPasswordUseCase
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val updateUserPasswordUseCase: UpdateUserPasswordUseCase,
) : ViewModel() {
    val resetPasswordState = ResetPasswordState(viewmodelScope = viewModelScope)

    val updatePasswordState: MutableStateFlow<UpdatePasswordState> =
        MutableStateFlow(UpdatePasswordState.None)

    val updateUpdatePasswordState: (UpdatePasswordState) -> Unit =
        { updatePasswordState.value = it }

    val updatePassword: (String, String) -> Unit = { email, password ->
        viewModelScope.launch {
            updateUserPasswordUseCase(
                UpdatePasswordInfo(email, password)
            ).collect { it ->
                when (val result = it) {
                    is DataState.Fail -> updatePasswordState.value =
                        UpdatePasswordState.Fail("비밀번호 변경을 실패하였습니다.")

                    is DataState.Success -> {
                        if (result.data) {
                            updatePasswordState.value = UpdatePasswordState.Success
                        } else {
                            updatePasswordState.value =
                                UpdatePasswordState.Fail(result.message ?: "비밀번호 변경을 실패하였습니다.")
                        }

                    }
                }
            }
        }
    }


}