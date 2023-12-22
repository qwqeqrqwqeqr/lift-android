package com.gradation.lift.oauth.common

/**
 * [OAuthConnectState]
 * OAuth 로그인 성공 및 실패 상태를 관리하는 상태
 * 초기상태는 [None]을 가짐
 * 실패시에는 오류 메시지를 포함하여 전달
 * @since 2023-08-27 13:09:35
 */
sealed interface OAuthConnectState {
    data object None : OAuthConnectState
    data object Success : OAuthConnectState
    data class Fail(val message: String) : OAuthConnectState
}