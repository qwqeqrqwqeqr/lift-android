package com.gradation.lift.feature.work.work.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.model.routine.RoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class WorkSharedViewModel @Inject constructor(
) : ViewModel() {


    val selectedRoutineSetList = MutableStateFlow(emptyList<RoutineSetRoutine>())


    val workList = MutableStateFlow("")


    fun updateSelectedRoutineSetList(routineSetRoutineList: List<RoutineSetRoutine>) {
        selectedRoutineSetList.update { it.plus(routineSetRoutineList) }
    }

}