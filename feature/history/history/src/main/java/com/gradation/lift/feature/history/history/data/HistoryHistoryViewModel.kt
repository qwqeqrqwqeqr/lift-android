package com.gradation.lift.feature.history.history.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryHistoryViewModel @Inject constructor(
    getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {


    val historyUiState: StateFlow<HistoryUiState> = getHistoryUseCase().map {
        when (it) {
            is DataState.Fail -> {
                HistoryUiState.None
            }
            is DataState.Success ->{
                if(it.data.isEmpty()){
                    HistoryUiState.Empty
                }else{
                    HistoryUiState.Success
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HistoryUiState.None
    )
}