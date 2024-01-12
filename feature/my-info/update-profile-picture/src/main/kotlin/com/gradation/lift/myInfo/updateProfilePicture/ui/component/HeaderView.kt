package com.gradation.lift.myInfo.updateProfilePicture.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                ) {
                    append("프로필 사진")
                }
                append("을 등록해주세요")
            },
            style = LiftTheme.typography.no1,
            color = LiftTheme.colorScheme.no11,
        )
    }

}

