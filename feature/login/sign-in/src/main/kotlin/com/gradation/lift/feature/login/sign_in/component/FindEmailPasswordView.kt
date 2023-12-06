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

/**
 * [FindEmailPasswordView]
 * 이메일 및 비밀번호 찾기와 연결되는 컴포넌트
 * 현재 해당 기능을 사용할 수 없기 때문에 임시 보류
 * @since 2023-08-17 13:28:52
 */
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
