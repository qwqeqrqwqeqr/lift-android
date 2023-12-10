package com.gradation.lift.createRoutine.routine.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.createRoutine.routine.data.state.WorkSetState
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    workSetState: WorkSetState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxWidth()
            .padding(LiftTheme.space.paddingSpace),
    ) {
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                currentRoutineSetRoutineState.appendRoutine(
                    Routine(
                        id = null,
                        routineSetId = 0,
                        workCategory = workCategory,
                        workSetList = workSetState.workSetList.map {
                            WorkSet(
                                weight = it.weight.toFloat(),
                                repetition = it.repetition.toInt()
                            )
                        }
                    )
                )
                navigateRoutineToRoutineSetInCreateRoutineGraph()
            },
            enabled = workSetState.workSetList.isNotEmpty()
        ) {
            Text(
                text = "등록하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = "CreateRoutine",
                modifier = modifier
                    .fillMaxHeight()
                    .width(LiftTheme.space.space8)
            )
        }
    }
}
