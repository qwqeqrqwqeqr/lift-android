package com.gradation.lift.feature.work.work.ui.component.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.label.LiftProgressLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.progress.LiftProgressCircle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.WorkState
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@Composable
fun WorkProgressCircle(
    modifier: Modifier = Modifier,
    progress: Float,
    workTime: LocalTime,
    workState: WorkState,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        LiftProgressCircle(
            modifier = modifier.align(Alignment.Center),
            progress = progress,
        )
        Column(
            modifier = modifier.width(LiftTheme.space.space148),
            verticalArrangement = Arrangement.spacedBy(
                LiftTheme.space.space12,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LiftText(
                modifier = modifier,
                textStyle = LiftTextStyle.No1,
                text = workState.getCurrentWorkRoutine().workCategory.name,
                color = LiftTheme.colorScheme.no11,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)

            ) {
                Icon(
                    painter = painterResource(LiftIcon.Timer),
                    contentDescription = "",
                    modifier = modifier.align(Alignment.CenterVertically)
                )
                Text(
                    modifier = modifier.align(Alignment.CenterVertically),
                    text =
                    with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
                        workTime.toJavaLocalTime().format(this)
                    },
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Right
                )
            }
            Row(
                modifier = modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                LiftText(
                    modifier = modifier,
                    textStyle = LiftTextStyle.No2,
                    text = "달성도",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Start
                )
                LiftProgressLabel(modifier, progress)
            }
        }
    }
}