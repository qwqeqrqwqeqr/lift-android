package com.gradation.lift

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val isSignedUseCase: IsSignedUseCase
): ViewModel() {

    val splashUiState:StateFlow<SplashUiState> = splashUiState(isSignedUseCase()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SplashUiState.Loading
    )

    fun setDefaultSystemUiController(systemUiController: SystemUiController){
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(Color.White)
        systemUiController.setSystemBarsColor(Color.White)
    }

    private fun splashUiState(isSignedUseCase: Flow<DataState<Boolean>>): Flow<SplashUiState> {
        return  isSignedUseCase.map {
            when(it){
                is DataState.Error -> SplashUiState.Login
                is DataState.Fail -> SplashUiState.Login
                is DataState.Success ->
                {
                    if(it.data) SplashUiState.Main else  SplashUiState.Login
                }
            }
        }

    }
}



sealed interface SplashUiState {
    object Loading : SplashUiState
    object Main : SplashUiState
    object Login : SplashUiState
}