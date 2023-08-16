package com.gradation.lift.state

sealed interface SplashUiState {
    object Loading : SplashUiState
    object Main : SplashUiState
    object Login : SplashUiState
    object RegisterDetail : SplashUiState
}