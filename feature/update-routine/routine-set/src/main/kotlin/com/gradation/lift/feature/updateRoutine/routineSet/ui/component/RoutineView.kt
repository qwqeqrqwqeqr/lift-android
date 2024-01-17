package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.filter.LiftAddContainer
import com.gradation.lift.designsystem.component.filter.LiftChangeOrderContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
internal fun RoutineView(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineSetToChangeOrderInUpdateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(LiftTheme.colorScheme.no5)
                .padding(horizontal = LiftTheme.space.space20),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "운동 목록",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LiftChangeOrderContainer(modifier.noRippleClickable { navigateRoutineSetToChangeOrderInUpdateRoutineGraph() })
                LiftAddContainer(modifier.noRippleClickable { navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() })
            }
        }
        RoutineListView(
            modifier,
            currentRoutineSetRoutine,
            currentRoutineSetRoutineState,
            navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph
        )
    }
}
