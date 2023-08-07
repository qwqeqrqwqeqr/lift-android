package com.gradation.lift.feature.work.work.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
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





    val workScreenState: MutableStateFlow<WorkScreenState> =
        MutableStateFlow(WorkScreenState.WorkScreen)
    internal var onVisibleSuspendDialog = MutableStateFlow(false)
    internal var onVisibleCompleteDialog = MutableStateFlow(false)


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


    fun updateWorkState(): (Boolean) -> Unit = {
        workState.update { it }
    }

    fun visibleSuspendDialog(): () -> Unit = {
        onVisibleSuspendDialog.value = true
    }

    fun invisibleSuspendDialog(): () -> Unit = {
        onVisibleSuspendDialog.value = false
    }

    fun visibleCompleteDialog(): () -> Unit = {
        onVisibleCompleteDialog.value = true
    }

    fun invisibleCompleteDialog(): () -> Unit = {
        onVisibleCompleteDialog.value = false
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


data class WorkRestTime(
    val workTime: LocalTime = LocalTime(0, 0, 0),
    val restTime: LocalTime = LocalTime(0, 0, 0),
    val totalTime: LocalTime = LocalTime(0, 0, 0),
)


