package com.gradation.lift.feature.work.work.ui.component.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.canvas.LiftProgressCircle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.state.WorkState
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@Composable
fun WorkProgressCircle(
    modifier: Modifier = Modifier,
    workState: WorkState,
    currentWork: WorkRoutine?,
    workProgress:Int,
    workTime: LocalTime,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        LiftProgressCircle(
            modifier = modifier.align(Alignment.Center),
            progress = workProgress,
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(
                LiftTheme.space.space4,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (currentWork?.workCategory?.name ?: "").replace(" ", "\n"),
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no11,
                textAlign = TextAlign.Center,
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
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                Text(
                    text = "달성도",
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no9,
                )
                Surface(
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier
                        .clip(RoundedCornerShape(LiftTheme.space.space4))
                        .align(
                            Alignment.CenterVertically
                        )
                ) {
                    Text(
                        modifier = modifier.padding(
                            horizontal = LiftTheme.space.space4,
                            vertical = LiftTheme.space.space2
                        ),
                        text = "${workProgress}%",
                        style = LiftTheme.typography.no5,
                        color = LiftTheme.colorScheme.no5,
                    )
                }

            }
        }
    }
}