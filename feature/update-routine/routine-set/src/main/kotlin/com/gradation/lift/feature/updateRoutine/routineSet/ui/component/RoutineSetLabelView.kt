package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun RoutineSetLabelView(
    modifier: Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState
) {
    LiftText(
        textStyle = LiftTextStyle.No3,
        text = "루틴 라벨",
        color = LiftTheme.colorScheme.no3,
        textAlign = TextAlign.Start
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf(
            Triple(
                Label.LABEL1,
                LiftIcon.RoutineLabel1,
                LiftIcon.CheckedRoutineLabel1
            ),
            Triple(
                Label.LABEL2,
                LiftIcon.RoutineLabel2,
                LiftIcon.CheckedRoutineLabel2
            ),
            Triple(
                Label.LABEL3,
                LiftIcon.RoutineLabel3,
                LiftIcon.CheckedRoutineLabel3
            ),
            Triple(
                Label.LABEL4,
                LiftIcon.RoutineLabel4,
                LiftIcon.CheckedRoutineLabel4
            ),
            Triple(
                Label.LABEL5,
                LiftIcon.RoutineLabel5,
                LiftIcon.CheckedRoutineLabel5
            )
        ).forEach {
            if (currentRoutineSetRoutine.label.contains(it.first))
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space28)
                        .noRippleClickable {
                            currentRoutineSetRoutineState.updateRoutineSetLabel(
                                currentRoutineSetRoutine.label.minus(it.first)
                            )
                        },
                    painter = painterResource(id = it.third),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            else Icon(
                modifier = modifier
                    .size(LiftTheme.space.space28)

                    .noRippleClickable {
                        currentRoutineSetRoutineState.updateRoutineSetLabel(
                            currentRoutineSetRoutine.label.plus(it.first)
                        )
                    },

                painter = painterResource(id = it.second),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}
