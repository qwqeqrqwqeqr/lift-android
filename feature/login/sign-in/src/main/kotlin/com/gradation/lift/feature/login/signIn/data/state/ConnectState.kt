package com.gradation.lift.feature.login.signIn.data.state

import com.gradation.lift.model.model.auth.LoginMethod


/**
 * [ConnectState]
 * OAuth 연결 상태, 성공 시 연결한 OAuth가 어떤 종류인지를 필드값에 넣어줌
 * @since 2023-12-27 15:21:17
 */
sealed interface ConnectState {

    data class Success(val loginMethod: LoginMethod, val isSigned: Boolean = true) : ConnectState
    data class Fail(val message: String) : ConnectState
    data object None : ConnectState
}