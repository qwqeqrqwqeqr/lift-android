package com.gradation.lift

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private  val _isSigned = MutableStateFlow( true )
    val isSigned = _isSigned.asStateFlow()


    private fun updateIsSinged(status:Boolean){
        _isSigned.value = status
    }

    fun setDefaultSystemUiController(systemUiController: SystemUiController){
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(Color.White)
        systemUiController.setSystemBarsColor(Color.White)
    }

    private fun splashUiState(signedUseCase: Flow<DataState<Boolean>>): Flow<SplashUiState> {
        return  signedUseCase.map {
            when(it){
                is DataState.Error -> SplashUiState.Success
                is DataState.Fail -> SplashUiState.Success
                is DataState.Success ->
                {
                    updateIsSinged(it.data)
                    if(it.data) {
                        SplashUiState.Success
                    } else{
                        SplashUiState.Success
                    }
                }
            }
        }
    }
}



sealed interface SplashUiState {
    object Success : SplashUiState
    object Loading : SplashUiState
}