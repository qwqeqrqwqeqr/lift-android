package com.gradation.lift.create_routine.routine

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gradation.lift.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateRoutineRoutineViewModel @Inject constructor(
) : ViewModel() {


    val workSetList = MutableStateFlow(emptyList<IndexWorkSet>())



    val updateWorkSet: (IndexWorkSet) -> Unit = { indexWorkSet ->
        workSetList.value = workSetList.value.also {
            with(it[indexWorkSet.index - 1]) {
                this.index = indexWorkSet.index
                this.weight = indexWorkSet.weight ?: 0f
                this.repetition = indexWorkSet.repetition ?: 0
            }
        }
    }

    val addWorkSet: () -> Unit = {
        workSetList.value = workSetList.value.plus(
            if (workSetList.value.isEmpty()) IndexWorkSet(1, 30f, 1) else {
                workSetList.value.last().copy(index = +1)
            }
        )
    }


    val removeWorkSet: (IndexWorkSet) -> Unit = { indexWorkSet ->
        workSetList.value = workSetList.value.minus(indexWorkSet)
    }


}


data class IndexWorkSet(
    var index: Int,
    var weight: Float?,
    var repetition: Int?
)