package com.gradation.lift.designsystem.component.progress

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

enum class ProgressCircleState { None, Current, Done }

@Composable
fun LiftProgressCircleLabel(
    modifier: Modifier = Modifier,
    state: ProgressCircleState = ProgressCircleState.None,
    number: Int,
) {
    val backgroundColor: Color by animateColorAsState(
        when (state) {
            ProgressCircleState.None -> LiftTheme.colorScheme.no1
            ProgressCircleState.Current -> LiftTheme.colorScheme.no5
            ProgressCircleState.Done -> LiftTheme.colorScheme.no4
        },
        label = "backgroundColor"
    )
    val textColor: Color by animateColorAsState(
        when (state) {
            ProgressCircleState.None -> LiftTheme.colorScheme.no2
            ProgressCircleState.Current -> LiftTheme.colorScheme.no4
            ProgressCircleState.Done -> LiftTheme.colorScheme.no5
        },
        label = "textColor"
    )
    val borderColor: Color by animateColorAsState(
        when (state) {
            ProgressCircleState.None -> Color.Transparent
            ProgressCircleState.Current -> LiftTheme.colorScheme.no4
            ProgressCircleState.Done -> Color.Transparent
        },
        label = "borderColor"
    )


    Box(
        modifier
            .size(LiftTheme.space.space32)
            .background(
                backgroundColor, shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 30.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            ProgressCircleState.None -> LiftText(
                textStyle = LiftTextStyle.No4,
                text = "$number",
                color = textColor,
                textAlign = TextAlign.Center
            )

            ProgressCircleState.Current -> LiftText(
                textStyle = LiftTextStyle.No3,
                text = "$number",
                color = textColor,
                textAlign = TextAlign.Center
            )

            ProgressCircleState.Done -> Icon(
                modifier = modifier.size(LiftTheme.space.space12),
                painter = painterResource(id = LiftIcon.Check),
                contentDescription = "Done",
                tint = textColor
            )
        }
    }
}