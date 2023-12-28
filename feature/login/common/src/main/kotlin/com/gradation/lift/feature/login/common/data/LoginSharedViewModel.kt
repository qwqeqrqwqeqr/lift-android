package com.gradation.lift.feature.login.common.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * [LoginSharedViewModel]
 * 로그인 모듈에서 공통적으로 사용하는 뷰모델
 * @since 2023-12-27 15:13:04
 */
@HiltViewModel
class LoginSharedViewModel @Inject constructor(
) : ViewModel() {

    val currentLoginMethodState: MutableStateFlow<LoginMethodState> =
        MutableStateFlow<LoginMethodState>(LoginMethodState.None)

    val updateCurrentLoginMethodState: (LoginMethodState) -> Unit = {
        currentLoginMethodState.value = it
    }

    val termsOfUseState: MutableStateFlow<TermsOfUseState> =
        MutableStateFlow(TermsOfUseState.TermsOfUse())

    val updateTermsOfUseState: (TermsOfUseState) -> Unit = {
        termsOfUseState.value = it
    }
}
