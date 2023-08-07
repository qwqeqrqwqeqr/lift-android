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


    private val openedWorkRoutineIdList = MutableStateFlow(emptySet<Int>())
    private val checkedWorkSetList = MutableStateFlow(emptySet<Pair<Int, Int>>())
    private val workList = MutableStateFlow(emptyList<WorkRoutineSelection>())


    val selectedWorkList = combine(
        workList,
        openedWorkRoutineIdList,
        checkedWorkSetList
    ) { selectedWorkList, openedWorkRoutineIdList, checkedWorkSetList ->
        selectedWorkList.map { workRoutineItem ->
            workRoutineItem.copy(
                opened = openedWorkRoutineIdList.contains(workRoutineItem.index),
                workSetList = workRoutineItem.workSetList.map { workSetItem ->
                    workSetItem.copy(
                        selected = checkedWorkSetList.contains(workSetItem.set)
                    )
                }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList<WorkRoutineSelection>()
    )


    fun addOpenedWorkRoutineId(): (Int) -> Unit =
        { value -> openedWorkRoutineIdList.update { it.plus(value) } }

    fun removeOpenedWorkRoutineId(): (Int) -> Unit =
        { value -> openedWorkRoutineIdList.update { it.minus(value) } }

    fun checkWorkSet(): (Pair<Int, Int>) -> Unit =
        { value -> checkedWorkSetList.update { it.plus(value) } }

    fun uncheckWorkSet(): (Pair<Int, Int>) -> Unit =
        { value -> checkedWorkSetList.update { it.minus(value) } }



    fun createWorkList(routineSetRoutineList: List<RoutineSetRoutine>) {
        workList.update {
            it.plus(routineSetRoutineList.flatMap { it.routine }
                .mapIndexed { routineIndex, routine ->
                    WorkRoutineSelection(
                        index = routineIndex,
                        workCategory = routine.workCategory,
                        opened = false,
                        workSetList = routine.workSetList.mapIndexed { workSetIndex, workSet ->
                            WorkSetSelection(
                                set = Pair(routineIndex, workSetIndex + 1),
                                weight = workSet.weight,
                                repetition = workSet.repetition,
                                selected = false
                            )
                        }
                    )
                })
        }
    }


}




