package com.gradation.lift.oauth.state

sealed interface OAuthSignInState {
    object None : OAuthSignInState
    object Success : OAuthSignInState
    data class Fail(val message: String) : OAuthSignInState
}