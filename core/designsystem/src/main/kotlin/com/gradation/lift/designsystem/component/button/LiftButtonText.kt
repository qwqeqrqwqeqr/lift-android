package com.gradation.lift.designsystem.component.button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun LiftButtonText(
    modifier: Modifier = Modifier,
    text: String,
    color:Color,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color =color,
        style = LiftTheme.typography.no3,
    )
}

@Composable
internal fun LiftSmallButtonText(
    modifier: Modifier = Modifier,
    text: String,
    color:Color,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = LiftTheme.typography.no8,
    )
}