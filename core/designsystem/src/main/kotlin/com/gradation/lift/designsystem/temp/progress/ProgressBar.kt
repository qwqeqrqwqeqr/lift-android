package com.gradation.lift.designsystem.temp.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftProgressBar(
    modifier: Modifier = Modifier,
    progressColor : Color,
    backgroundColor: Color,
    progress: Int,
) {
    Canvas(modifier = modifier.padding(LiftTheme.space.paddingSpace)) {
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
            color = backgroundColor
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
           color=progressColor,
        )
    }
}


@Composable
@Preview
fun LiftProgressBarPreview() {
    LiftMaterialTheme {
        LiftProgressBar(
            modifier = Modifier.size(196.dp),
            progressColor = Color.Blue,
            backgroundColor = Color.White,
            progress = 10
        )
    }
}
