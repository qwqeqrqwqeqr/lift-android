package com.gradation.lift.designsystem.component.label

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
fun LiftNumberLabel(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Box(
        modifier = modifier
            .size(LiftTheme.space.space20)
            .background(
                color = LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(size = LiftTheme.space.space4)
            )
            .padding(horizontal = LiftTheme.space.space4),
        contentAlignment = Alignment.Center
    ) {
        LiftText(
            textStyle = LiftTextStyle.No8,
            text = number.toString(),
            color = LiftTheme.colorScheme.no4,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LiftNumberLabelPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column(modifier=modifier.fillMaxSize()) {
            LiftNumberLabel(number = 1)
            LiftNumberLabel(number = 2)
            LiftNumberLabel(number = 3)
            LiftNumberLabel(number = 4)
            LiftNumberLabel(number = 5)

        }
    }
}
