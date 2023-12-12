package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun RoutineView(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "운동 목록",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )

        LiftOutlineButton(
            modifier = modifier
                .height(LiftTheme.space.space32),
            contentPadding = PaddingValues(
                start = LiftTheme.space.space16, end = LiftTheme.space.space16
            ),
            onClick = navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
        ) {
            Text(
                text = "추가",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(LiftTheme.space.space2))
            Icon(
                painterResource(id = LiftIcon.Plus),
                contentDescription = null,
            )
        }
    }
    RoutineListView(
        modifier,
        currentRoutineSetRoutine,
        currentRoutineSetRoutineState,
    )
}
