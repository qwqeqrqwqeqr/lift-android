package com.gradation.lift.feature.register_detail.gender.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(
    modifier: Modifier=Modifier,
    name :String
){
    Text(
        text = buildAnnotatedString {
            append("${name}님의 ")
            withStyle(
                style = SpanStyle(color = LiftTheme.colorScheme.no4),
            ) {
                append("성별")
            }
            append("은 무엇인가요?")
        },
        style = LiftTheme.typography.no1,
        color = LiftTheme.colorScheme.no11,
    )
}