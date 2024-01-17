package com.gradation.lift.state

sealed interface SplashState {
    data object Loading : SplashState
    data object Main : SplashState
    data object Login : SplashState
    data object RegisterDetail : SplashState
}