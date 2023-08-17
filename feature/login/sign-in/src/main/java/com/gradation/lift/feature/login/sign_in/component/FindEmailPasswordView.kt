package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun FindEmailPasswordView(
    modifier: Modifier = Modifier,
    findEmailPassword: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ClickableText(
            text = AnnotatedString("이메일 찾기"),
            style = LiftTheme.typography.no7 +
                    TextStyle(color = LiftTheme.colorScheme.no7),
            onClick = {
                findEmailPassword()
            },
        )

        Spacer(modifier = modifier.padding(2.dp))
        Divider(
            thickness = 1.dp, modifier = modifier
                .width(1.dp)
                .height(10.dp)
        )
        Spacer(modifier = modifier.padding(2.dp))

        ClickableText(
            text = AnnotatedString("비밀번호 찾기"),
            style = LiftTheme.typography.no7 +
                    TextStyle(color = LiftTheme.colorScheme.no7),
            onClick = { offset ->
                findEmailPassword()
            },
        )
    }
}
