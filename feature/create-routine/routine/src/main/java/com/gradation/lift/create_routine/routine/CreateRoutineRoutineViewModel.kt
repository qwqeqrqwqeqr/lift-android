package com.gradation.lift.create_routine.routine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CreateRoutineRoutineViewModel @Inject constructor(
) : ViewModel() {


    val workSetList = MutableStateFlow(emptyList<IndexWorkSet>())


    val updateWorkSet: (IndexWorkSet) -> Unit = { indexWorkSet ->
        workSetList.value = workSetList.value.map {
            it.copy(
                index = if (it.index == indexWorkSet.index) indexWorkSet.index else it.index,
                weight = if (it.index == indexWorkSet.index) indexWorkSet.weight else it.weight,
                repetition = if (it.index == indexWorkSet.index) indexWorkSet.repetition else it.repetition
            )
        }
    }

    val addWorkSet: () -> Unit = {
        workSetList.value = workSetList.value.plus(
            if (workSetList.value.isEmpty()) IndexWorkSet(1, "30", "1") else {
                workSetList.value.last().let { it.copy(index = it.index + 1) }
            }
        )
    }


    val removeWorkSet: (IndexWorkSet) -> Unit = { indexWorkSet ->
        workSetList.value = workSetList.value.minus(indexWorkSet)
    }


    val createRoutineCondition = workSetList.map { it.isNotEmpty() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

}


data class IndexWorkSet(
    var index: Int, var weight: String, var repetition: String
)