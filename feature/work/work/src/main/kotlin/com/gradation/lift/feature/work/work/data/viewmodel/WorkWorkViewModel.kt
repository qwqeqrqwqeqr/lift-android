package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [WorkWorkViewModel]
 * @property workDialogUiState 현재 팝업에 관련한 상태
 * @property workScreenUiState 현저 화면에 관련한 상태
 * @property autoCompleteState 주어진 운동을 모두 완료할 경우 자동완료 팝업을 보이게끔 도와주는 상태 (한번만 작동하게끔 설정할 것)
 */
@HiltViewModel
class WorkWorkViewModel @Inject constructor(
) : ViewModel() {


    val workDialogUiState: MutableStateFlow<WorkDialogUiState> = MutableStateFlow(WorkDialogUiState.None)
    val workScreenUiState: MutableStateFlow<WorkScreenUiState> =
        MutableStateFlow(WorkScreenUiState.WorkScreenUi)
    val autoCompleteState: MutableStateFlow<Boolean> = MutableStateFlow(true)



    fun updateWorkScreenState(): (WorkScreenUiState) -> Unit = { state ->
        when (workScreenUiState.value) {
            WorkScreenUiState.ListScreenUi(true) -> {
                workScreenUiState.update { WorkScreenUiState.WorkScreenUi }
            }
            WorkScreenUiState.ListScreenUi(false) -> {
                workScreenUiState.update { WorkScreenUiState.RestScreenUi }
            }
            else -> {
                workScreenUiState.update { state }
            }
        }
    }

    fun updateWorkDialogState(): (WorkDialogUiState) -> Unit = {
        workDialogUiState.value = it
    }

    fun offAutoCompleteState(): () -> Unit = {
        autoCompleteState.value = false
    }
}





