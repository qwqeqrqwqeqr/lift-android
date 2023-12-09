package com.gradation.lift.feature.routineDetail.routine.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    routineSetRoutine: RoutineSetRoutine,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxWidth()
            .padding(LiftTheme.space.paddingSpace),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10),
        verticalAlignment = Alignment.Bottom
    ) {
        LiftOutlineButton(
            modifier = modifier.weight(1f),
            shape = RoundedCornerShape(size = LiftTheme.space.space12),
            onClick = {
                navigateRoutineDetailGraphToUpdateRoutineGraph(routineSetRoutine.id)
            },
        ) {
            Text(
                text = "루틴 수정",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
            )
        }
        LiftButton(
            modifier = modifier.weight(1f),
            onClick = { navigateRoutineDetailGraphToWorkWorkRouter(routineSetRoutine.id) },
        ) {
            Text(
                text = "운동 시작",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}