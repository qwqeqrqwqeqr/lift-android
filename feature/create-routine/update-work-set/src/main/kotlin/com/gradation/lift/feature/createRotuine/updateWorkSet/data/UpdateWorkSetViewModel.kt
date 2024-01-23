package com.gradation.lift.feature.createRotuine.updateWorkSet.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.model.WorkSet
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.workCategoryUiState
import com.gradation.lift.model.model.routine.Routine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [UpdateWorkSetViewModel]
 * @property workCategoryUiState 운동 카테고리 화면 상태
 * @since 2023-12-08 18:38:24
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


