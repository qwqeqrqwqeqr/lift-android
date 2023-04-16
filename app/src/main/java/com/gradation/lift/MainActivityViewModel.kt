package com.gradation.lift

import androidx.lifecycle.ViewModel

class MainActivityViewModel  : ViewModel() {
}


sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val data: Any) : MainActivityUiState
    data class Error(val data: Any) : MainActivityUiState
}