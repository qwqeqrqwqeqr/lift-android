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

@Composable
fun LiftProgressCircle(
    modifier: Modifier = Modifier,
    progress: Int,
) {
    val maxProgress = 275f
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(top = 8.dp, start = 8.dp)
            .size(196.dp),
    ) {


        Canvas(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            drawArc(
                color = Color(0xFFF2F2F5),
                startAngle = 135f,
                sweepAngle = maxProgress,
                useCenter = false,
                size = Size(512.dp.value, 512.dp.value),
                style = Stroke(width = 48f, cap = StrokeCap.Round)
            )

            drawArc(
                color = Color(0xFF0080FF),
                startAngle = 135f,
                sweepAngle = progress / 100f * maxProgress,
                useCenter = false,
                size = Size(512.dp.value, 512.dp.value),
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

