package com.gradation.lift.feature.register_detail.name.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
) {
    LiftMultiStyleText(
        modifier=modifier,
        defaultColor = LiftTheme.colorScheme.no11,
        defaultTextStyle = LiftTextStyle.No1,
        textAlign = TextAlign.Center,
        textWithStyleList = listOf(
            TextWithStyle(text = "원활한 리프트 사용을 위해 \n"),
            TextWithStyle(text = "추가정보", color = LiftTheme.colorScheme.no4),
            TextWithStyle(text = "를 등록할게요"),
        )
    )
}