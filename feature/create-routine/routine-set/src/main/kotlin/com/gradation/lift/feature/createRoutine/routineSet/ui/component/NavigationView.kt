package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState

@Composable
internal fun NavigationView(
    modifier: Modifier = Modifier,
    updateCondition: Boolean,
    routineSetScreenState: RoutineSetScreenState,
) {
    LiftDefaultBottomBar(
        modifier = modifier
    ) {
        LiftSolidButton(
            modifier = modifier,
            enabled = updateCondition,
            text = "생성하기",
            onClick = { routineSetScreenState.updateCompleteDialogView(true) }
        )
    }
}
