package com.gradation.lift.designsystem.component.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun WorkPartLabel(
    modifier: Modifier = Modifier,
    workPart: String,
) {
    Row(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no1,
                RoundedCornerShape(LiftTheme.space.space4)
            )
            .padding(horizontal = LiftTheme.space.space4, vertical = LiftTheme.space.space2),
    ) {
        LiftText(
            modifier = modifier,
            text = workPart,
            textStyle = LiftTextStyle.No5,
            color = LiftTheme.colorScheme.no4,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(
    showBackground = true,
)
fun WorkPartLabelPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(modifier = modifier.background(LiftTheme.colorScheme.no11)) {
            WorkPartLabel(modifier, "등")
            WorkPartLabel(modifier, "가슴")
            WorkPartLabel(modifier, "하체")
        }
    }
}

