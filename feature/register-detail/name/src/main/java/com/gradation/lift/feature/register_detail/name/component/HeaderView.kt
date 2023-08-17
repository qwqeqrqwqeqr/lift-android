package com.gradation.lift.feature.register_detail.name.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
) {
    Text(
        text = buildAnnotatedString {
            append("원활한 리프트 사용을 위해 \n")
            withStyle(
                style = SpanStyle(color = LiftTheme.colorScheme.no4),
            ) {
                append("추가정보")
            }
            append("를 등록할게요")
        },
        style = LiftTheme.typography.no1,
        color = LiftTheme.colorScheme.no11,
    )
}