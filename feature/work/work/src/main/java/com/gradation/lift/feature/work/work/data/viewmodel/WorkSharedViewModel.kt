package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.feature.work.work.data.model.WorkRoutineSelection
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.work.WorkCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class WorkSharedViewModel @Inject constructor(
) : ViewModel() {

    private val routineSetRoutineList = MutableStateFlow(emptyList<RoutineSetRoutine>())
    private val openedWorkRoutineIdList = MutableStateFlow(emptySet<Int>())
    private val checkedWorkSetList = MutableStateFlow(emptySet<Pair<Int, Int>>())
    private val currentWorkIndex = MutableStateFlow(0)


    val workList = combine(
        routineSetRoutineList,
        openedWorkRoutineIdList,
        checkedWorkSetList
    ) { routineSetRoutineList, openedWorkRoutineIdList, checkedWorkSetList ->

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
        initialValue = emptyList<WorkRoutineSelection>()
    )


    val workProgress = workList
        .map { it.flatMap { it.workSetList }.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 0
        )




    val currentWork = combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
        workList.find { it.index == currentWorkIndex } ?: workList.first()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = workList.value.first()
    )
    val previousWork = combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
        workList.find { it.index == currentWorkIndex - 1 }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = workList.value.first()
    )
    val nextWork = combine(currentWorkIndex, workList) { currentWorkIndex, workList ->
        workList.find { it.index == currentWorkIndex + 1 }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = workList.value.first()
    )


    fun addOpenedWorkRoutineId(): (Int) -> Unit =
        { value -> openedWorkRoutineIdList.update { it.plus(value) } }

    fun removeOpenedWorkRoutineId(): (Int) -> Unit =
        { value -> openedWorkRoutineIdList.update { it.minus(value) } }

    fun checkWorkSet(): (Pair<Int, Int>) -> Unit =
        { value -> checkedWorkSetList.update { it.plus(value) } }

    fun uncheckWorkSet(): (Pair<Int, Int>) -> Unit =
        { value -> checkedWorkSetList.update { it.minus(value) } }


    fun updateRoutineSetRoutineList(value: List<RoutineSetRoutine>) {
        routineSetRoutineList.update { it.plus(value) }
    }

    fun updateWorkIndexToPreviousIndex(): () -> Unit = {
        currentWorkIndex.update { it - 1 }
    }
    fun updateWorkIndexToNextIndex(): () -> Unit = {
        currentWorkIndex.update { it + 1 }
    }

}






