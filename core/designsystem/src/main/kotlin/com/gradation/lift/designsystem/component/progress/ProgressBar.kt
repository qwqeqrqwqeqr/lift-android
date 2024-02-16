package com.gradation.lift.designsystem.component.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    stroke: Dp = LiftTheme.space.space16,
    progressBarColor: Color = LiftTheme.colorScheme.no4,
    progressBarBackgroundColor: Color = LiftTheme.colorScheme.no1,
) {

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20)
    ) {
        drawPath(
            path = Path().apply {
                moveTo(0f, center.y)
                lineTo(
                    size.width, center.y
                )
                close()
            },
            style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            color = progressBarBackgroundColor
        )

        drawPath(
            path = Path().apply {
                moveTo(
                    0f,
                    center.y
                )
                lineTo(
                    progress / 100f * size.width,
                    center.y
                )
                close()
            },
            style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            color = progressBarColor,
        )
    }


}


@Composable
@Preview
fun LiftProgressBarPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        LiftProgressBar(
            modifier = modifier,
            stroke = LiftTheme.space.space16,
            progress = 30f,
        )
    }
}
