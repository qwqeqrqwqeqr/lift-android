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
    private val initTimerUseCase: InitTimerUseCase
) : ViewModel() {


    private val workState = MutableStateFlow(true)
    val workRestTime = MutableStateFlow(WorkRestTime())


    val workDialogState: MutableStateFlow<WorkDialogState> = MutableStateFlow(WorkDialogState.None)
    val workScreenState: MutableStateFlow<WorkScreenState> =
        MutableStateFlow(WorkScreenState.WorkScreen)


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
        workDialogState.value =  it
    }


    fun updateWorkState(): (Boolean) -> Unit = {
        workState.value =it
    }



    fun startTimer() {
        viewModelScope.launch {
            combine(
                initTimerUseCase(),
                workState,
            ) { currentTime, workState ->

                if (workState) {
                    workRestTime.update {
                        it.copy(
                            totalTime = fromSecondOfDay(currentTime),
                            workTime = fromSecondOfDay((it.workTime.toSecondOfDay() + 1))
                        )
                    }
                } else {
                    workRestTime.update {
                        it.copy(
                            totalTime = fromSecondOfDay(currentTime),
                            restTime = fromSecondOfDay((it.restTime.toSecondOfDay() + 1))
                        )
                    }
                }
            }.collect()
        }
    }
}





