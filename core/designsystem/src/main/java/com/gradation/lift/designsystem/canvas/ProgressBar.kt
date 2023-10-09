package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme


@Composable
fun LiftProgressBar(
    modifier: Modifier = Modifier,
    progress: Int,
) {
    Canvas(modifier = modifier.padding(16.dp)) {
        drawPath(
            path = Path().apply {
                moveTo(
                    0f,
                    center.y
                )
                lineTo(
                    size.width, center.y
                )
                close()
            },
            style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            color = Color(0xFFF2F2F5)
        )

        drawPath(
            path = Path().apply {
                moveTo(
                    0f,
                    center.y
                )
                lineTo(
                    progress.toFloat() / 100 * size.width, center.y
                )
                close()
            },
            style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF0080FF),
                    Color(0xffffffff)
                ), startX = 0f, endX = size.width
            )
        )
    }
}


@Composable
@Preview
fun LiftProgressBarPreview() {
    LiftMaterialTheme {
        LiftProgressBar(
            modifier = Modifier.size(196.dp),
            progress = 10
        )
    }
}
