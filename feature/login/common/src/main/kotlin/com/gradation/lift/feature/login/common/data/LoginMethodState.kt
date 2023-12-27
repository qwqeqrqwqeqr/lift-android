package com.gradation.lift.feature.login.common.data


/**
 * [LoginMethodState]
 * 현재 로그인 방식이 무엇인지에 대해 정의한 상태
 * @since 2023-12-27 15:13:04
 */
sealed interface LoginMethodState{

    data class Default(
        val email : String,
        val password: String
    ) : LoginMethodState

    data object Kakao : LoginMethodState
    data object Naver : LoginMethodState
    data object Google : LoginMethodState
    data object None : LoginMethodState

}