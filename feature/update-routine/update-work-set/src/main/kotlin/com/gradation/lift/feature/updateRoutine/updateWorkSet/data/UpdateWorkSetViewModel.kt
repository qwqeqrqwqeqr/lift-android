package com.gradation.lift.feature.updateRoutine.updateWorkSet.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.model.WorkSet
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.routine.Routine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [UpdateWorkSetViewModel]
 * @since 2024-01-10 15:02:02
 */
@HiltViewModel
class UpdateWorkSetViewModel @Inject constructor(
) : ViewModel() {

    val workSetState = WorkSetState()

    val setRoutine: (Routine) -> Unit = {
        workSetState.workSetList.clear()
        it.workSetList.forEachIndexed { index, workSet ->
            workSetState.workSetList.add(
                WorkSet(
                    setNumber = index + 1,
                    weight = workSet.weight.toString(),
                    repetition = workSet.repetition.toString(),
                )
            )
        }
        workSetState.workCategory = it.workCategory
    }

}


