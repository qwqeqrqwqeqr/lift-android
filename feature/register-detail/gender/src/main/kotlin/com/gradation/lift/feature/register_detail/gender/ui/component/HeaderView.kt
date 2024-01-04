package com.gradation.lift.feature.register_detail.gender.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(
    modifier: Modifier=Modifier,
    name :String
){
    LiftMultiStyleText(
        modifier=modifier,
        defaultColor = LiftTheme.colorScheme.no11,
        defaultTextStyle = LiftTextStyle.No1,
        textAlign = TextAlign.Center,
        textWithStyleList = listOf(
            TextWithStyle(text = "${name}님의 "),
            TextWithStyle(text = "성별", color = LiftTheme.colorScheme.no4),
            TextWithStyle(text = "은 무엇인가요?"),
        )
    )
}