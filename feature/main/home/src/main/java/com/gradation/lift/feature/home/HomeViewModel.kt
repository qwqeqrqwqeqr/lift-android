package com.gradation.lift.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    ViewModel() {


}


sealed interface HomeUiState {
    data class Success(val userData: List<com.gradation.lift.model.work.WorkCategory>) : HomeUiState
    object Loading : HomeUiState
    object Empty : HomeUiState
    object Error : HomeUiState
}