package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun HeaderView(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                append(text = "매일매일 운동하고, 기록하고! \n")
                withStyle(
                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                ) {
                    append(text = "나만의 운동파트너, 리프트")
                }
            },
            style = LiftTheme.typography.no1,
            lineHeight = 30.sp,
            color = LiftTheme.colorScheme.no11,
        )
    }
}

