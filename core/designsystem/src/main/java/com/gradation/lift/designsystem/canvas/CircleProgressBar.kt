package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
fun LiftCircleProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
) {
    val maxProgress = 275f
    Box(
        modifier = modifier
            .size(196.dp)
            .padding(16.dp),
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = Color(0xFFF2F2F5),
                startAngle = 135f,
                sweepAngle = maxProgress,
                useCenter = false,
                topLeft = Offset(x = center.x / 8, y = center.y / 8),
                size = Size(384.dp.value, 384.dp.value),
                style = Stroke(width = 32f, cap = StrokeCap.Round)
            )
        }
        Canvas(modifier = modifier) {
            drawArc(
                color = Color(0xFF0080FF),
                startAngle = 135f,
                sweepAngle = progress / 100f * maxProgress,
                useCenter = false,
                topLeft = Offset(x = center.x / 8, y = center.y / 8),
                size = Size(384.dp.value, 384.dp.value),
                style = Stroke(width = 32f, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
@Preview
fun LiftCircleProgressBarPreview() {
    LiftMaterialTheme {
        LiftCircleProgressBar(
            modifier = Modifier.size(196.dp),
            progress = 35f,
        )
    }
}

