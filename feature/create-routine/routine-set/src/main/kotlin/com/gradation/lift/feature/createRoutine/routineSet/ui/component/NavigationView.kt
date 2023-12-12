package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState

@Composable
internal fun NavigationView(
    modifier: Modifier = Modifier,
    updateCondition: Boolean,
    routineSetScreenState: RoutineSetScreenState
) {
    Row(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .fillMaxWidth()
            .padding(LiftTheme.space.paddingSpace),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10),
        verticalAlignment = Alignment.Bottom
    ) {
        LiftButton(
            modifier = modifier.weight(1f),
            enabled = updateCondition,
            onClick = { routineSetScreenState.updateCompleteDialogView(true) }
        ) {
            Text(
                text = "생성하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}
