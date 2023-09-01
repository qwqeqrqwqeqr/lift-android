package com.gradation.lift.designsystem.chart.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.chart.model.WorkHexagonChartItem
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun WorkHexagonChart(
    modifier: Modifier = Modifier,
    item: WorkHexagonChartItem
) {
    val sliceWidth = with(LocalDensity.current) { 20.dp.toPx() }

    val needlePaint = remember { Paint().apply { color = Color(0xFFCFCFD9) } }

    val firstColor = LiftTheme.colorScheme.no4
    val defaultLineColor = LiftTheme.colorScheme.no8

    BoxWithConstraints(
        modifier = modifier
            .height(360.dp)
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5),
    ) {


        Canvas(
            modifier =
            modifier
                .height(this@BoxWithConstraints.maxHeight / 2)
                .width(this@BoxWithConstraints.maxWidth / 2)
                .align(Alignment.Center)

        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height


            val heightFirstInterval = (size.height / 4)
            val heightSecondInterval = (size.height / 4 * 3)


            drawPath(
                path = Path().apply {
                    moveTo(center.x, 0f)
                    lineTo(canvasWidth, heightFirstInterval)
                    lineTo(canvasWidth, heightSecondInterval)
                    lineTo(center.x, canvasHeight)
                    lineTo(0f, heightSecondInterval)
                    lineTo(0f, heightFirstInterval)
                    close()
                },
                style = Stroke(width = 1.dp.toPx()),
                color = defaultLineColor
            )
        }
    }
}

@Preview
@Composable
fun WorkHexagonChartPreview() {
    val modifier: Modifier = Modifier
    LiftMaterialTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            WorkHexagonChart(
                modifier = modifier,
                item =
                WorkHexagonChartItem(
                    chestValue = 10,
                    shoulderValue = 20,
                    armValue = 30,
                    backValue = 40,
                    lowerBodyValue = 10,
                    absValue = 30,
                )
            )
        }
    }
}