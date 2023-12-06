package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.work.data.model.*
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalTime
import javax.inject.Inject

/**
 * [WorkSharedViewModel]
 * @property routineSetRoutineList 현재 설정 되어있는 루틴세트의 목록
 * @property workState 운동 상태를 관리하는 모델
 * @property historyWorkRestTime 운동 시간 정보, 기록에 등록하기 위한 필드로 실질적인 계산은 [WorkWorkViewModel]에서 진행
 * @property historyRoutine 운동 루틴 정보을 기록에 등록하기 위한 필드  완료되지 않은 세트는 기록에 저장되지않는다.
 * @since 2023-08-22 15:58:22
 */
@HiltViewModel
class WorkSharedViewModel @Inject constructor(
    initTimerUseCase: InitTimerUseCase,
) : ViewModel() {

    private val routineSetRoutineList: MutableStateFlow<List<RoutineSetRoutine>> =
        MutableStateFlow(emptyList())


    val workState = WorkState(
        viewModelScope = viewModelScope,
        initTimerUseCase = initTimerUseCase,
        routineSetRoutineList = routineSetRoutineList
    )


    val historyWorkRestTime: StateFlow<WorkRestTime> = workState.workRestTime.map {
        WorkRestTime(
            workTime = it.workTime,
            restTime = it.restTime,
            totalTime = LocalTime.fromSecondOfDay(it.workTime.toSecondOfDay() + it.restTime.toSecondOfDay())
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WorkRestTime()
    )

    val historyRoutine: StateFlow<List<CreateHistoryRoutine>> = workState.workList.map { workList ->
        workList
            .filter { it.workSetList.count { it.selected } != 0 }
            .map {
                CreateHistoryRoutine(
                    workCategory = it.workCategory.name,
                    workSetList = it.workSetList.filter { it.selected }.map {
                        WorkSet(
                            weight = it.weight,
                            repetition = it.repetition
                        )
                    }
                )
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /**
     * 현재 모든 운동이 체크 되었는지 여부
     * (모든 운동을 완료 했는지)
     * 해당 메서드를 바탕으로 완료 팝업을 보여 줄지 결정함
     * @since 2023-08-22 16:11:02
     */
    fun isAllCheckedWorkSet(): (List<WorkSetSelection>) -> Boolean = { workSetList ->
        workSetList.count() == workSetList.count { it.selected }
    }



    fun updateRoutineSetRoutineList(): (List<RoutineSetRoutine>) -> Unit = { value ->
        routineSetRoutineList.update { it.plus(value) }
    }


}







