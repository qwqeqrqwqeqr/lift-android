package com.gradation.lift.state

sealed interface SplashState {
    object Loading : SplashState
    object Main : SplashState
    object Login : SplashState
    object RegisterDetail : SplashState
}