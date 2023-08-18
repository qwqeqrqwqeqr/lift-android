package com.gradation.lift.feature.register_detail.height_weight.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(
    modifier: Modifier=Modifier
){
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(color = LiftTheme.colorScheme.no4),
            ) {
                append("키")
            }
            append("와 ")
            withStyle(
                style = SpanStyle(color = LiftTheme.colorScheme.no4),
            ) {
                append("몸무게")
            }
            append("를 입력해주세요")
        },
        style = LiftTheme.typography.no1,
        color = LiftTheme.colorScheme.no11,
    )
}