package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme




@Composable
fun NumberCircle(
    modifier: Modifier = Modifier,
    checked: Boolean = true,
    number: Int,
) {
    val baseColor = if (checked) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no1
    val border = if (checked) LiftTheme.colorScheme.no4 else Color.Transparent
    val textStyle = if (checked) LiftTheme.typography.no3 else LiftTheme.typography.no4
    val textColor = if (checked) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no2
    Box(
        modifier
            .size(30.dp)
            .background(
                baseColor, shape = RoundedCornerShape(size = 30.dp)
            )
            .border(
                width = 2.dp,
                color = border,
                shape = RoundedCornerShape(size = 30.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = number.toString(),
            style = textStyle,
            color = textColor
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