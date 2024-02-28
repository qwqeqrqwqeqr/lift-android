package com.gradation.lift.feature.login.common.data

import com.gradation.lift.ui.utils.TermsPolicyContent.MARKTETING_CONENT
import com.gradation.lift.ui.utils.TermsPolicyContent.PRIVACY_POLICY_CONTENT
import com.gradation.lift.ui.utils.TermsPolicyContent.TERMS_OF_USE_CONTENT


/**
 * [TermsOfUseState]
 * 현재 로그인 방식이 무엇인지에 대해 정의한 상태
 * @since 2023-12-27 15:13:04
 */
sealed interface TermsOfUseState{

    data class TermsOfUse(
        val title : String = "서비스 이용약관",
        val content:String = TERMS_OF_USE_CONTENT
    ) : TermsOfUseState

    data class PrivacyPolicy(
        val title : String = "개인정보 처리방침",
        val content:String = PRIVACY_POLICY_CONTENT
    ) : TermsOfUseState
    data class Marketing(
        val title : String = "마케팅 수신정보",
        val content:String = MARKTETING_CONENT
    ) : TermsOfUseState

}