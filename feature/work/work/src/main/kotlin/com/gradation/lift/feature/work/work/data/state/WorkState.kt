package com.gradation.lift.feature.work.work.data.state

import com.gradation.lift.domain.usecase.timer.InitTimerUseCase
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.model.WorkRoutineSelection
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import javax.inject.Inject

/**
 * [WorkState]
 * 운동 상태를 관리하는 모델
 * @property openedWorkRoutineIdList 루틴의 상세정보가 열려 있는 루틴세트 아이디 목록
 * @property checkedWorkSetList 현재 체크(완료)한 운동들의 목록
 * @property currentWorkIndex 현재 운동의 인덱스 번호 (현재 어떤 운동을 진행 하는지 확인하기 위함)
 * @property workList 운동 목록, 루틴세트 목록을 바탕으로 생성함
 * @property workProgress 운동의 진행도 [checkedWorkSetList] 를 바탕으로 계산함
 * @property workState 현재 운동 상태(운동,휴식)  true -> 운동 false -> 휴식
 * @property workRestTime  운동, 휴식, 및 종합 시간을 저장하고 있는 모델
 * @property currentWork 현재 진행하고 있는 운동
 * @property previousWork 이전 순번 운동 [currentWorkIndex] 바탕으로 존재 여부를 판단함
 * @property nextWork 다음 순번 운동 [currentWorkIndex] 바탕으로 존재 여부를 판단함
 * @since 2023-09-01 15:49:50
 */
class WorkState @Inject constructor(
    private val viewModelScope: CoroutineScope,
    private val initTimerUseCase: InitTimerUseCase,
    routineSetRoutineList: MutableStateFlow<List<RoutineSetRoutine>>,
) {
    private lateinit var timerJob: Job
    private val checkedWorkSetList: MutableStateFlow<Set<Pair<Int, Int>>> =
        MutableStateFlow(emptySet())

    private val openedWorkRoutineIdList: MutableStateFlow<Set<Int>> = MutableStateFlow(emptySet())
    private val currentWorkIndex: MutableStateFlow<Int> = MutableStateFlow(0)


    val workList: StateFlow<List<WorkRoutineSelection>> = combine(
        routineSetRoutineList,
        openedWorkRoutineIdList,
        checkedWorkSetList
    )
    { routineSetRoutineList, openedWorkRoutineIdList, checkedWorkSetList ->

        (routineSetRoutineList.flatMap { it.routine }
            .mapIndexed { routineIndex, routine ->
                WorkRoutineSelection(
                    index = routineIndex,
                    workCategory = routine.workCategory,
                    opened = openedWorkRoutineIdList.contains(routineIndex),
                    workSetList = routine.workSetList.mapIndexed { workSetIndex, workSet ->
                        WorkSetSelection(
                            set = Pair(routineIndex, workSetIndex + 1),
                            weight = workSet.weight,
                            repetition = workSet.repetition,
                            selected = checkedWorkSetList.contains(
                                Pair(
                                    routineIndex,
                                    workSetIndex + 1
                                )
                            )
                        )
                    }
                )
            })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val workProgress: StateFlow<Int> = workList.map {
        ((it.flatMap { it.workSetList.filter { it.selected } }.count()
            .toFloat() / it.flatMap { it.workSetList }.count().toFloat()) * 100).toInt()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = MIN_PROGRESS
    )


    private val workState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val workRestTime: MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime())


    val currentWork: StateFlow<WorkRoutineSelection> =
        combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
            workList.find { it.index == currentWorkIndex } ?: workList.first()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = WorkRoutineSelection()
        )
    val previousWork: StateFlow<WorkRoutineSelection?> =
        combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
            workList.find { it.index == currentWorkIndex - 1 }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    val nextWork: StateFlow<WorkRoutineSelection?> =
        combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
            workList.find { it.index == currentWorkIndex + 1 }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun updateOpenedWorkRoutine(): (WorkRoutineSelection) -> Unit =
        { value ->
            if (value.opened) {
                openedWorkRoutineIdList.update { it.minus(value.index) }
            } else {
                openedWorkRoutineIdList.update { it.plus(value.index) }
            }
        }

    fun updateCheckedWorkSet(): ((Pair<Int, Int>), Boolean) -> Unit =
        { value, checked ->
            if (checked) {
                checkedWorkSetList.update { it.plus(value) }
            } else {
                checkedWorkSetList.update {
                    it.minus(value)
                }

            }
        }

    fun updateWorkIndexToPreviousIndex(): () -> Unit = {
        currentWorkIndex.update { it - 1 }
    }

    fun updateWorkIndexToNextIndex(): () -> Unit = {
        currentWorkIndex.update { it + 1 }
    }


    fun updateWorkState(): (Boolean) -> Unit = {
        workState.value = it
    }


    fun startTimer() {
        timerJob = viewModelScope.launch {
            initTimerUseCase().map { _ ->
                if (workState.value) {
                    workRestTime.update {
                        it.copy(
                            workTime = LocalTime.fromSecondOfDay((it.workTime.toSecondOfDay() + 1))
                        )
                    }
                } else {
                    workRestTime.update {
                        it.copy(
                            restTime = LocalTime.fromSecondOfDay((it.restTime.toSecondOfDay() + 1))
                        )
                    }
                }
            }.collect()
        }
    }

    fun stopTime() {
        timerJob.cancel()
    }


    companion object {
        const val MIN_PROGRESS = 0
        const val MAX_PROGRESS = 100
    }
}