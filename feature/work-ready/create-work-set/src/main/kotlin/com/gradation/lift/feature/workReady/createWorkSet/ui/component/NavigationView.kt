package com.gradation.lift.feature.workReady.createWorkSet.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutine
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutineWorkSet
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    workSetState: WorkSetState,
    workRoutineState: WorkRoutineState,
    navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph: () -> Unit,
) {

    LiftDefaultBottomBar(
        modifier = modifier

    ) {
        LiftSolidButton(
            modifier = modifier,
            enabled = workSetState.workSetList.isNotEmpty()
                    && workSetState.workSetList.none {
                it.weight.isEmpty() || !decimalNumberValidator(
                    it.weight
                )
            }
                    && workSetState.workSetList.none {
                it.repetition.isEmpty() || it.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                    it.repetition
                )
            },
            text = "등록하기",
            onClick = {
                workRoutineState.appendRoutine(
                    WorkReadyRoutine(
                        id = workRoutineState.currentWorkReadyRoutineList.takeUnless { it.isEmpty() }
                            ?.let { it.maxOf { it.id } + 1 } ?: 0,
                        workCategoryId = workCategory.id,
                        workCategoryName = workCategory.name,
                        workPart = workCategory.workPart,
                        workSetList = workSetState.workSetList.map {
                            WorkReadyRoutineWorkSet(
                                weight = it.weight,
                                repetition = it.repetition
                            )
                        }.toMutableStateList()
                    )
                )
                navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph()
            }
        )
    }
}
