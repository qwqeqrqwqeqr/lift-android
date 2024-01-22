package com.gradation.lift.designsystem.component.label

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftProgressLabel(
    modifier: Modifier = Modifier,
    progress: Float,
) {
    Box(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(size = LiftTheme.space.space4)
            )
            .padding(horizontal = LiftTheme.space.space6, vertical = LiftTheme.space.space4),
        contentAlignment = Alignment.Center
    ) {
        LiftText(
            textStyle = LiftTextStyle.No8,
            text = "${progress.toInt()}%",
            color = LiftTheme.colorScheme.no4,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LiftProgressLabelPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column(modifier = modifier.fillMaxSize()) {
            LiftProgressLabel(progress = 50f)
        }
    }
}
