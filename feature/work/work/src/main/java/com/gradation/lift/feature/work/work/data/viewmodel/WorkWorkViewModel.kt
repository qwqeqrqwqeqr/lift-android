package com.gradation.lift.feature.work.work.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.state.WorkDialogState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay
import javax.inject.Inject

@HiltViewModel
class WorkWorkViewModel @Inject constructor(
) : ViewModel() {



    val workDialogState: MutableStateFlow<WorkDialogState> = MutableStateFlow(WorkDialogState.None)
    val workScreenState: MutableStateFlow<WorkScreenState> =
        MutableStateFlow(WorkScreenState.WorkScreen)
    val autoCompleteState: MutableStateFlow<Boolean> = MutableStateFlow(true)


    fun updateWorkScreenState(): (WorkScreenState) -> Unit = { state ->
        when (workScreenState.value) {
            WorkScreenState.ListScreen(true) -> {
                workScreenState.update { WorkScreenState.WorkScreen }
            }
            WorkScreenState.ListScreen(false) -> {
                workScreenState.update { WorkScreenState.RestScreen }
            }
            else -> {
                workScreenState.update { state }
            }
        }
    }

    fun updateWorkDialogState(): (WorkDialogState) -> Unit = {
        workDialogState.value = it
    }

    fun offAutoCompleteState(): () -> Unit = {
        autoCompleteState.value = false
    }
}





