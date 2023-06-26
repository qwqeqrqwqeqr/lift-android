package com.gradation.lift.splash

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun splashUiState(signedUseCase: Flow<DataState<Boolean>>): Flow<SplashUiState> {
    return  signedUseCase.map {
        delay(2000L)
        when(it){
            is DataState.Error -> SplashUiState.Main
            is DataState.Fail -> SplashUiState.Main
            is DataState.Success ->
            {
                if(it.data) SplashUiState.Main else SplashUiState.Login
            }
        }
    }
}

sealed interface SplashUiState {
    object Login : SplashUiState
    object Main : SplashUiState
    object Loading : SplashUiState
}