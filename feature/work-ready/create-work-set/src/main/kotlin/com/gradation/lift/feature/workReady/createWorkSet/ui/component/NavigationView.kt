package com.gradation.lift.feature.workReady.createWorkSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.feature.workReady.common.model.WorkRoutineWorkSet
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    workSetState: WorkSetState,
    workRoutineState: WorkRoutineState,
    navigateCreateWorkSetToReadyInWorkReadyGraph: () -> Unit,
) {

    LiftDefaultContainer(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxWidth(),
        shape = RectangleShape,
        verticalPadding = LiftTheme.space.space10,
        horizontalPadding = LiftTheme.space.space20
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
                    WorkRoutine(
                        id = workRoutineState.currentWorkRoutine.takeUnless { it.isEmpty() }
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
                navigateCreateWorkSetToReadyInWorkReadyGraph()
            }
        )
    }
}
