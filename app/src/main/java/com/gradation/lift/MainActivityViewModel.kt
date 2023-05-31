package com.gradation.lift

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
): ViewModel() {
}


sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val data: Any) : MainActivityUiState
    data class Error(val data: Any) : MainActivityUiState
}