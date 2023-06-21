package com.gradation.lift.designsystem.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PlusCircle(modifier: Modifier =Modifier){
    Canvas(
        modifier = modifier
            .size(32.dp)
    ) {

        drawCircle(
            color = Color(0xFF0080FF)
        )
        drawLine(
            color = Color.White,
            start = Offset(x = center.x, y = (0f+size.center.y)/2),
            end = Offset(x = center.x, y = (size.height+size.center.y)/2),
            strokeWidth= 8f
        )

        drawLine(
            color = Color.White,
            start = Offset(x = (0f+size.center.x)/2, y = center.y),
            end = Offset(x = (size.width+size.center.x)/2, y = center.y),
            strokeWidth= 8f
        )
    }
}



@Composable
@Preview
fun PlusCirclePreview(){
    PlusCircle()
}