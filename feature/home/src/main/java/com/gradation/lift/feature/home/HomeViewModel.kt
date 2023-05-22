package com.gradation.lift.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras.Empty.map
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.GetWorkCategoryUseCase
import com.gradation.lift.model.data.WorkPart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(getWorkCategoryUseCase: GetWorkCategoryUseCase) :
    ViewModel() {

    val uiState: StateFlow<HomeUiState> = getWorkCategoryUseCase().map { result ->
        when (result) {
            is DataState.Success -> {
                result.data?.let { (HomeUiState.Success(it)) } ?: (HomeUiState.Empty)
            }
            is DataState.Loading -> {
                (HomeUiState.Loading)
            }
            is DataState.Error -> {
                (HomeUiState.Error)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading,
    )
}


sealed interface HomeUiState {
    data class Success(val userData: List<WorkPart>) : HomeUiState

    object Loading : HomeUiState
    object Empty : HomeUiState
    object Error : HomeUiState
}