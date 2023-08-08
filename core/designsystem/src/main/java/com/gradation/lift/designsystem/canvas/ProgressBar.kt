package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme


@Composable
fun LiftProgressBar(
    modifier: Modifier = Modifier,
    progress: Int,
) {
    Canvas(modifier = modifier.padding(16.dp)) {
        drawLine(
            color = Color(0xFFF2F2F5),
            start = Offset(x = 0f, y = center.y),
            end = Offset(x = size.width, y = center.y),
            strokeWidth = 64f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color(0xFF0080FF),
            start = Offset(x = 0f, y = center.y),
            end = Offset(x = progress.toFloat() / 100 * size.width, y = center.y),
            strokeWidth = 64f,
            cap = StrokeCap.Round
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
