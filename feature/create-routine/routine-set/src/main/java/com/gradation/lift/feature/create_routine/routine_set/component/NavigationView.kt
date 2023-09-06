package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    createRoutineCondition: Boolean,
    createRoutineSetRoutine: () -> Unit,
){
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = createRoutineSetRoutine,
        enabled = createRoutineCondition
    ) {
        Text(
            text = "생성하기",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}