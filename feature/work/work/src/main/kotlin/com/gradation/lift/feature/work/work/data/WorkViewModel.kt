package com.gradation.lift.feature.work.work.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.work.ClearWorkUseCase
import com.gradation.lift.feature.work.common.data.state.ClearWorkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    private val clearWorkUseCase: ClearWorkUseCase,
) : ViewModel() {

    val clearWorkState: MutableStateFlow<ClearWorkState> = MutableStateFlow(ClearWorkState.None)
    val updateClearWorkState: (ClearWorkState) -> Unit = { clearWorkState.value = it }


    val clearWork: () -> Unit = {
        viewModelScope.launch {
            clearWorkUseCase().collect {
                when (it) {
                    is DataState.Fail -> updateClearWorkState(ClearWorkState.None)
                    is DataState.Success -> updateClearWorkState(ClearWorkState.Success)
                }
            }
        }
    }

}