package com.gradation.lift.feature.work.common.data

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.domain.usecase.work.FetchWorkUseCase
import com.gradation.lift.domain.usecase.work.LoadWorkUseCase
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.common.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.Constants.WORK_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import javax.inject.Inject

@HiltViewModel
class WorkSharedViewModel @Inject constructor(
    initTimerUseCase: InitTimerUseCase,
    private val loadWorkUseCase: LoadWorkUseCase,
    private val fetchWorkUseCase: FetchWorkUseCase,

    ) : ViewModel() {


    val historyRoutineList: MutableStateFlow<List<CreateHistoryRoutine>> =
        MutableStateFlow(emptyList())
    val historyWorkRestTime: MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime())
    val historyProgress: MutableStateFlow<Float> = MutableStateFlow(0f)

    val setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit = {
        historyRoutineList.value = it
    }
    val setHistoryWorkRestTime: (WorkRestTime) -> Unit = {
        historyWorkRestTime.value = it
    }
    val setHistoryProgress: (Float) -> Unit = {
        historyProgress.value = it
    }
    val usedRoutineSetIdList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    val workState = WorkState(initTimerUseCase, viewModelScope)

    val workRoutineInfoState: WorkRoutineInfoState = WorkRoutineInfoState()


    val fetchWork: () -> Unit = {
        viewModelScope.launch {
            fetchWorkUseCase(
                Work(
                    id = WORK_ID_KEY,
                    restTime = workState.restTime.value,
                    workTime = workState.workTime.value,
                    totalTime = LocalTime.fromSecondOfDay(workState.workTime.value.toSecondOfDay() + workState.restTime.value.toSecondOfDay()),
                    routine = workState.workRoutineList.map { workRoutine ->
                        com.gradation.lift.model.model.work.WorkRoutine(
                            workId = WORK_ID_KEY,
                            workRoutineId = workRoutine.workRoutineId,
                            workCategoryId = workRoutine.workCategoryId,
                            workCategoryName = workRoutine.workCategoryName,
                            workPart = workRoutine.workPart,
                            workSetList = workRoutine.workSetList.map { workSet ->
                                WorkSet(
                                    workSetId = workSet.id,
                                    weight = workSet.weight.toFloat(),
                                    repetition = workSet.repetition.toInt()
                                )
                            }
                        )
                    },
                    usedRoutineSetIdList = usedRoutineSetIdList.value,
                    checkedWorkSetInfoList = workRoutineInfoState.checkedWorkSetList.toList()
                )
            ).collect()
        }
    }

    val initWork: () -> Unit = {
        viewModelScope.launch {
            loadWorkUseCase().collect {
                when (val result = it) {
                    is DataState.Fail -> {}
                    is DataState.Success -> {
                        if (workState.workRoutineList.toList().isEmpty()) {
                            workState.workRoutineList.addAll(result.data.routine.map { workRoutine ->
                                WorkRoutine(
                                    WORK_ID_KEY,
                                    workRoutine.workRoutineId,
                                    workRoutine.workCategoryId,
                                    workRoutine.workCategoryName,
                                    workRoutine.workPart,
                                    workRoutine.workSetList.map { workSet ->
                                        WorkRoutineWorkSet(
                                            id = workSet.workSetId,
                                            weight = workSet.weight.toString(),
                                            repetition = workSet.repetition.toString()
                                        )
                                    }.toMutableStateList()
                                )
                            })
                            workRoutineInfoState.checkedWorkSetList.addAll(result.data.checkedWorkSetInfoList)
                            usedRoutineSetIdList.value = result.data.usedRoutineSetIdList
                            workState.workRestTime.value =
                                WorkRestTime(
                                    workTime = result.data.workTime,
                                    restTime = result.data.restTime,
                                    totalTime = result.data.totalTime
                                )


                            workState.startTimer()
                            usedRoutineSetIdList.update { result.data.usedRoutineSetIdList }
                        }
                    }
                }
            }
        }
    }

    init {
        initWork()
    }

}