package com.gradation.lift.feature.routineDetail.routine.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    routineSetRoutine: RoutineSetRoutine,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkReadyReadyRoute: (String) -> Unit,
) {
    LiftDefaultBottomBar(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10),
            verticalAlignment = Alignment.Bottom
        ) {
            LiftPrimaryButton(
                modifier = modifier.weight(1f),
                text = "루틴 수정",
                onClick = { navigateRoutineDetailGraphToUpdateRoutineGraph(routineSetRoutine.id) }
            )
            LiftSolidButton(
                modifier = modifier.weight(1f),
                text = "운동 시작",
                onClick = { navigateRoutineDetailGraphToWorkReadyReadyRoute(routineSetRoutine.id.toString()) }
            )
        }
    }
}