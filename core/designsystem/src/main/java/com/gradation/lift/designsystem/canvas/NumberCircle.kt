package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalTextApi::class)
@Composable
fun NumberCircle(
    modifier: Modifier = Modifier,
    checked: Boolean = true,
    number: Int,
) {
    val textMeasure = rememberTextMeasurer()
    val textStyle = if (checked) SpanStyle(
        color = LiftTheme.colorScheme.no4,
        fontStyle = LiftTheme.typography.no3.fontStyle,
        fontWeight = LiftTheme.typography.no3.fontWeight,
        fontFamily = LiftTheme.typography.no3.fontFamily,
    ) else SpanStyle(
        color = LiftTheme.colorScheme.no2,
        fontStyle = LiftTheme.typography.no4.fontStyle,
        fontWeight = LiftTheme.typography.no4.fontWeight,
        fontFamily = LiftTheme.typography.no4.fontFamily,
    )
    val baseColor = if (checked) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1
    val innerColor = LiftTheme.colorScheme.no5
    Canvas(
        modifier = modifier
            .size(30.dp)
    ) {
        drawCircle(color = baseColor)

        if (checked) {
            drawCircle( 
                color = innerColor,
                radius = 35f
            )
        }

        drawText(
            textMeasurer = textMeasure,
            text = buildAnnotatedString {
                withStyle(style = textStyle) {
                    append(number.toString())
                }
            },
            topLeft = Offset(center.x - 10f, center.y - 30f)
        )


    }


}


@Composable
@Preview
fun NumberCirclePreview() {
    LiftMaterialTheme {
        Row {
            NumberCircle(
                checked = true,
                number = 1
            )
            NumberCircle(
                checked = false,
                number = 2
            )
        }
    }


}