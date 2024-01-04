package com.gradation.lift.feature.login.signInDefault.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInDefaultUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInDefaultViewModel @Inject constructor(
    signInDefaultUseCase: SignInDefaultUseCase,
    existUserDetail: ExistUserDetailUseCase,
) : ViewModel() {
    var signInState: MutableStateFlow<SignInState> = MutableStateFlow<SignInState>(SignInState.None)
    var updateSignInState: (SignInState) -> Unit = { signInState.value = it }

    val signIn: (String, String) -> Unit = { email, password ->
        viewModelScope.launch {
            if (email.isEmpty() || email.trim().isEmpty()) {
                signInState.value = SignInState.Fail("아이디를 입력해주세요.")
            } else if(password.isEmpty() || password.trim().isEmpty()){
                signInState.value = SignInState.Fail("비밀번호를 입력해주세요.")
            }
            else{
                signInDefaultUseCase(
                    DefaultSignInInfo(
                        id = email,
                        password = password
                    )
                ).collect{
                    when(it){
                        is DataState.Fail -> signInState.value = SignInState.Fail(it.message)
                        is DataState.Success -> {
                            existUserDetail().collect { existUserDetailResult ->
                                when (existUserDetailResult) {
                                    is DataState.Fail -> {
                                        signInState.value = SignInState.Fail("로그인에 실패하였습니다.")
                                    }

                                    is DataState.Success -> signInState.value =
                                        SignInState.Success(existUserDetailResult.data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}