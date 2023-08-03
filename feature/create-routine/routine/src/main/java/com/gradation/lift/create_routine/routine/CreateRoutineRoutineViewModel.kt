package com.gradation.lift.create_routine.routine

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gradation.lift.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CreateRoutineRoutineViewModel @Inject constructor(
) : ViewModel() {


    val workSetList = MutableStateFlow(emptyList<IndexWorkSet>())


    val updateWorkSet: (IndexWorkSet) -> Unit = { indexWorkSet ->
        Log.d("value", indexWorkSet.toString())
        Log.d("리스트 전", workSetList.value.toString())
        workSetList.value = workSetList.value.map {
            it.copy(
                index = if (it.index == indexWorkSet.index) indexWorkSet.index else it.index,
                weight = if (it.index == indexWorkSet.index) indexWorkSet.weight else it.weight,
                repetition = if (it.index == indexWorkSet.index) indexWorkSet.repetition else it.repetition
            )
        }
        Log.d("리스트 후", workSetList.value.toString())
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


}


data class IndexWorkSet(
    var index: Int, var weight: String, var repetition: String
)