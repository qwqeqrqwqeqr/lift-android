package com.gradation.lift.designsystem.extensions

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer


@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawText(
    textMeasurer: TextMeasurer,
    text: AnnotatedString,
) {
}