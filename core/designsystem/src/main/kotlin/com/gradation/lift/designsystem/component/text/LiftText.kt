package com.gradation.lift.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun LiftText(
    modifier: Modifier = Modifier,
    textStyle: LiftTextStyle,
    text: String,
    color: Color,
    textAlign: TextAlign,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        style = textStyle.toStyle(),
        textDecoration = textDecoration,
        overflow=overflow
    )
}





