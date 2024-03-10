package com.gradation.lift.feature.work.createWorkSet.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.utils.Constants.WORK_ID_KEY

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    workSetState: WorkSetState,
    workState: WorkState,
    navigateCreateWorkSetToWorkInWorkGraph: () -> Unit,
) {

    LiftDefaultBottomBar(
        modifier = modifier

    ) {
        LiftSolidButton(
            modifier = modifier,
            enabled = workSetState.workSetList.isNotEmpty()
                    && workSetState.workSetList.none {
                it.weight.isEmpty() || !decimalNumberValidator(it.weight)
            }
                    && workSetState.workSetList.none {
                it.repetition.isEmpty() || it.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                    it.repetition
                )
            },
            text = "등록하기",
            onClick = {
                workState.appendRoutine(
                    WorkRoutine(
                        id = WORK_ID_KEY,
                        workRoutineId = workState.workRoutineList.takeIf { it.isNotEmpty() }
                            ?.let { it.maxOf { it.workRoutineId } + 1 } ?: 0,
                        workCategoryId = workCategory.id,
                        workCategoryName = workCategory.name,
                        workPart = workCategory.workPart,
                        workSetList = workSetState.workSetList.map {
                            WorkRoutineWorkSet(
                                id = it.id,
                                weight = it.weight,
                                repetition = it.repetition
                            )
                        }.toMutableStateList()
                    )
                )
                navigateCreateWorkSetToWorkInWorkGraph()
            }
        )
    }
}
