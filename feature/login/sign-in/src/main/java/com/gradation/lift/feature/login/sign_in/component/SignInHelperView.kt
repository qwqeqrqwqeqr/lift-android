package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun SignInHelperView(
    modifier: Modifier = Modifier,
    onClickFindEmailPassword: () -> Unit,
    autoLoginChecked: Boolean,
    onChangeAutoLoginChecked: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            ToggleCheckbox(
                checked = autoLoginChecked,
                onCheckedChange = onChangeAutoLoginChecked,
                modifier= modifier.size(18.dp)
            )
            Spacer(modifier = modifier.padding(2.dp))
            Text(
                text = "자동 로그인",
                style = LiftTheme.typography.no7,
                color = LiftTheme.colorScheme.no7,
            )
        }

        FindIdPasswordView(
            onClickFindEmailPassword = onClickFindEmailPassword,
        )
    }
}


@Composable
fun FindIdPasswordView(
    modifier: Modifier = Modifier,
    onClickFindEmailPassword: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ClickableText(
            text = AnnotatedString("이메일 찾기"),
            style = LiftTheme.typography.no7 +
                    TextStyle(color =  LiftTheme.colorScheme.no7),
            onClick = {
                onClickFindEmailPassword()
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
                    TextStyle(color =  LiftTheme.colorScheme.no7),
            onClick = { offset ->
                onClickFindEmailPassword()
            },
        )
    }
}




