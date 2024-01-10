package com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    routineIndex: Int?,
    workSetState: WorkSetState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
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
                    && workSetState.workSetList.none() { it.weight.isEmpty() }
                    && workSetState.workSetList.none() { it.repetition.isEmpty() },
            text = "등록하기",
            onClick = {
                currentRoutineSetRoutineState.updateRoutine(
                    routineIndex!!,
                    Routine(
                        id = null,
                        routineSetId = 0,
                        workCategory = workSetState.workCategory!!,
                        workSetList = workSetState.workSetList.map {
                            WorkSet(
                                weight = it.weight.toFloat(),
                                repetition = it.repetition.toInt()
                            )
                        }
                    )
                )
                navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph()
            }
        )
    }
}
