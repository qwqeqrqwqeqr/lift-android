package com.gradation.lift.feature.updateRoutine.createWorkSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.createWorkSet.data.state.WorkSetState
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    workSetState: WorkSetState,
    routineUiState: RoutineUiState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateCreateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
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
                    && workSetState.workSetList.none() {
                it.weight.isEmpty() || it.weight.toFloatOrNull() == 0f || !decimalNumberValidator(
                    it.weight
                )
            }
                    && workSetState.workSetList.none() {
                it.repetition.isEmpty() || it.weight.toIntOrNull() == 0 || !decimalNumberValidator(
                    it.repetition
                )
            },
            text = "등록하기",
            onClick = {
                if (routineUiState is RoutineUiState.Success) {
                    currentRoutineSetRoutineState.appendRoutine(
                        Routine(
                            id = null,
                            routineSetId = routineUiState.routineSetRoutine.id,
                            workCategory = workCategory,
                            workSetList = workSetState.workSetList.map {
                                WorkSet(
                                    weight = it.weight.toFloat(),
                                    repetition = it.repetition.toInt()
                                )
                            }
                        )
                    )
                    navigateCreateWorkSetToRoutineSetInCreateRoutineGraph()
                }
            }
        )
    }
}
