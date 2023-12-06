package com.gradation.lift.designsystem.component.button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun LiftButtonText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = LiftTheme.typography.no3
    )
}