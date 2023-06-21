package com.gradation.lift

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
): ViewModel() {



    

    fun isLogin():Boolean{
        return false
    }

}


sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val data: Any) : MainActivityUiState
}