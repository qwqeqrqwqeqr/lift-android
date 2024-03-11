package com.gradation.lift.designsystem.component.progress

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
            .let {
                if (state == ProgressCircleState.Current)
                    it.shadow(
                        elevation = LiftTheme.space.space8,
                        spotColor = LiftTheme.colorScheme.no4,
                        ambientColor = LiftTheme.colorScheme.no4,
                        shape = CircleShape
                    )
                else it
            }
            .background(
                backgroundColor, shape = CircleShape
            )

            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
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

/**
 * [LiftExerciseSequenceCircleLabel]
 * 운동 방식 순서를 표기하기 위해 사용되는 라벨
 * @since 2024-03-11 12:24:34
 */
@Composable
fun LiftExerciseSequenceCircleLabel(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Box(
        modifier

            .size(LiftTheme.space.space32)
            .background(
                LiftTheme.colorScheme.no5, shape = CircleShape
            )

            .border(
                width = 2.dp,
                color = LiftTheme.colorScheme.no17,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {
        LiftText(
            textStyle = LiftTextStyle.No4,
            text = "$number",
            color = LiftTheme.colorScheme.no4,
            textAlign = TextAlign.Center
        )
    }
}


