package com.gradation.lift.feature.login.sign_in.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.LoginSignInScreen
import com.gradation.lift.ui.DevicePreview

@Composable
fun SignInHelperView(
    modifier: Modifier = Modifier,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
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
            onClickFindEmail = onClickFindEmail,
            onClickFindPassword = onClickFindPassword
        )
    }
}


@Composable
fun FindIdPasswordView(
    modifier: Modifier = Modifier,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ClickableText(
            text = AnnotatedString("이메일 찾기"),
            style = LiftTheme.typography.no7 +
                    TextStyle(color =  LiftTheme.colorScheme.no7),
            onClick = { offset ->
                onClickFindEmail()
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
                onClickFindPassword()
            },
        )
    }
}




