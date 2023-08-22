package com.gradation.lift.feature.work.routine_selection.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    selectedRoutineCount: Int,
    selectedRoutineSetList: List<RoutineSetRoutine>,
    updateRoutineSetRoutineList: (List<RoutineSetRoutine>) -> Unit,
    navigateSelectionRoutineToWork: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        LiftButton(
            onClick = {
                updateRoutineSetRoutineList(selectedRoutineSetList)
                navigateSelectionRoutineToWork()
            },
            modifier=modifier.fillMaxWidth(),
            enabled = selectedRoutineCount != 0
        ) {
            Text(
                text = "운동시작하기 (${selectedRoutineCount}개)",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .width(8.dp)
            )
        }
    }

}