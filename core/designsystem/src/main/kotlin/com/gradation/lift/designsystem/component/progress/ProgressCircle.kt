package com.gradation.lift.designsystem.component.progress


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftProgressCircle(
    modifier: Modifier = Modifier,
    progress: Float,
    progressBarColor: Color = LiftTheme.colorScheme.no4,
    progressBarBackgroundColor: Color = LiftTheme.colorScheme.no1,
) {
    val maxProgress = 245f
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(LiftTheme.space.space266),
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
                startAngle = 150f,
                sweepAngle = maxProgress,
                useCenter = false,
                size = Size(canvasWidth.toPx(), canvasHeight.toPx()),
                style = Stroke(width = 96f, cap = StrokeCap.Round)
            )

            drawArc(
                color = progressBarColor,
                startAngle = 150f,
                sweepAngle = progress / 100f * maxProgress,
                useCenter = false,
                size = Size(canvasWidth.toPx(), canvasHeight.toPx()),
                style = Stroke(width = 96f, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
@Preview
fun LiftProgressCirclePreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {


            LiftProgressCircle(
                modifier = modifier,
                progress = 30f,
            )
        }
    }
}

