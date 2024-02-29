package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun NavigationView(
    modifier: Modifier = Modifier,
    updateCondition: Boolean,
    currentRoutineSetRoutine: RoutineSetRoutine,
    updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit,
    routineSetScreenState: RoutineSetScreenState,
) {
    LiftDefaultBottomBar(
        modifier = modifier

    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10)) {
            LiftPrimaryButton(
                modifier = modifier.weight(1f),
                text = "삭제",
                onClick = { routineSetScreenState.updateDeleteDialogView(true) }
            )

            LiftSolidButton(
                modifier = modifier.weight(1f),
                enabled = updateCondition && currentRoutineSetRoutine.name.isNotEmpty(),
                text = "저장",
                onClick = { updateRoutineSetRoutine(currentRoutineSetRoutine) }
            )
        }
    }
}
