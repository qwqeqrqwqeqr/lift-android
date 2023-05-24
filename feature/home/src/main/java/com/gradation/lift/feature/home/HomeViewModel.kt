package com.gradation.lift.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gradation.lift.domain.model.WorkPart
import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.usecase.work.GetWorkCategoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(getWorkCategoryUseCase: GetWorkCategoryUseCase) : ViewModel() {

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
        started = SharingStarted.WhileSubscribed(10_000),
        initialValue = HomeUiState.Loading,
    )
}


sealed interface HomeUiState {
    data class Success(val userData: List<WorkPart>) : HomeUiState
    object Loading : HomeUiState
    object Empty : HomeUiState
    object Error : HomeUiState
}