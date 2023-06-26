package com.gradation.lift

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    
): ViewModel() {
    private val splashColor = Color(0xFF0080FF)

    fun setDefaultSystemUiController(systemUiController: SystemUiController){
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(Color.White)
        systemUiController.setSystemBarsColor(Color.White)
    }

    fun setSplashSystemUiController(systemUiController: SystemUiController){
        systemUiController.setStatusBarColor(splashColor)
    }


    fun isLogin():Boolean{
        return false
    }

}


sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val data: Any) : MainActivityUiState
}