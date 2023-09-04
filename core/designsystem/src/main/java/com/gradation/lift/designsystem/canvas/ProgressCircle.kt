package com.gradation.lift.designsystem.canvas


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftProgressCircle(
    modifier: Modifier = Modifier,
    progress: Int,
    progressBarColor: Color = LiftTheme.colorScheme.no4,
    progressBarBackgroundColor: Color = LiftTheme.colorScheme.no1
) {

    val maxProgress = 275f
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(top = 8.dp, start = 8.dp)
            .size(196.dp),
    ) {
        val canvasHeight = this.maxHeight
        val canvasWidth = this.maxWidth
        Canvas(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            drawArc(
                color = progressBarBackgroundColor,
                startAngle = 135f,
                sweepAngle = maxProgress,
                useCenter = false,
                size = Size(canvasWidth.toPx(), canvasHeight.toPx()),
                style = Stroke(width = 48f, cap = StrokeCap.Round)
            )

            drawArc(
                color = progressBarColor,
                startAngle = 135f,
                sweepAngle = progress / 100f * maxProgress,
                useCenter = false,
                size = Size(canvasWidth.toPx(), canvasHeight.toPx()),
                style = Stroke(width = 48f, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
@Preview
fun LiftProgressCirclePreview() {
    LiftMaterialTheme {
        LiftProgressCircle(
            modifier = Modifier,
            progress = 35,
        )
    }
}

