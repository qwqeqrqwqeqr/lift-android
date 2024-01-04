package com.gradation.lift.feature.login.termsOfUse.data.state


/**
 * [CreateUserTermsConsentState]
 * 이용약관 동의 정보를 생성하는 상태
 * @since 2023-12-28 22:27:42
 */
sealed interface CreateUserTermsConsentState {

    data object Success : CreateUserTermsConsentState
    data class Fail(val message: String) : CreateUserTermsConsentState
    data object None : CreateUserTermsConsentState
}