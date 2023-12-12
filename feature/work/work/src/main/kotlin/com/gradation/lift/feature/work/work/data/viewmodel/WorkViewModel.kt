package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.work.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.data.state.WorkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [WorkViewModel]
 */
@HiltViewModel
class WorkViewModel @Inject constructor(
    initTimerUseCase: InitTimerUseCase,
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase,
) : ViewModel() {


    val workState = WorkState(initTimerUseCase, viewModelScope)


    private var routineSetIdList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val setRoutineSetIdList: (IntArray?) -> Unit = {
        routineSetIdList.value = it?.toList() ?: emptyList()
    }


    val workUiState: StateFlow<WorkUiState> =
        routineSetIdList.flatMapLatest {
            getRoutineSetRoutineByRoutineSetIdUseCase(it.toSet())
        }.map { routineSetRoutine ->
            when (routineSetRoutine) {
                is DataState.Fail -> WorkUiState.Fail("운동목록 불러오기를 실패하였습니다.")
                is DataState.Success -> with(routineSetRoutine.data) {
                    if (this.isEmpty()) WorkUiState.Fail(
                        "운동목록 불러오기를 실패하였습니다."
                    )
                    else {
                        workState.currentWorkRoutineList.clear()
                        workState.currentWorkRoutineList.addAll(
                            this.flatMap { it.routine }.mapIndexed { workRoutineIndex, routine ->
                                WorkRoutine(
                                    key = workRoutineIndex + 1,
                                    workCategory = routine.workCategory,
                                    workSetList = routine.workSetList.mapIndexed { workSetIndex, workSet ->
                                        WorkRoutineWorkSet(
                                            key = workSetIndex + 1,
                                            weight = workSet.weight,
                                            repetition = workSet.repetition
                                        )
                                    }.toMutableStateList()
                                )
                            }
                        )
                        workState.startTimer()
                        WorkUiState.Success(this)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkUiState.Loading
        )

}





