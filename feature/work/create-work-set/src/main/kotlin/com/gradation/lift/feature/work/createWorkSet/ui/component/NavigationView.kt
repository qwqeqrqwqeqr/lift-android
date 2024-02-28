package com.gradation.lift.feature.work.createWorkSet.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.feature.work.common.data.WorkState
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineWorkSet
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.work.WorkCategory

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
                        id = workState.workRoutineList.takeUnless { it.isEmpty() }
                            ?.let { it.maxOf { it.id } + 1 } ?: 0,
                        workCategory = workCategory,
                        workSetList = workSetState.workSetList.map {
                            WorkRoutineWorkSet(
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
