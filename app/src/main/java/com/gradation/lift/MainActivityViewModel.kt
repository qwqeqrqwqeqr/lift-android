package com.gradation.lift

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    private fun splashUiState(signedUseCase: Flow<DataState<Boolean>>): Flow<SplashUiState> {
        return  signedUseCase.map {
            when(it){
                is DataState.Error -> SplashUiState.Main
                is DataState.Fail -> SplashUiState.Main
                is DataState.Success ->
                {
                    if(it.data) {
                        SplashUiState.Main
                    } else{
                        SplashUiState.Login
                    }
                }
            }
        }
    }
}



sealed interface SplashUiState {
    object Login : SplashUiState
    object Main : SplashUiState
    object Loading : SplashUiState
}