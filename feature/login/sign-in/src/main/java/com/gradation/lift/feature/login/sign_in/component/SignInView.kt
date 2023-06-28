package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.LoginSignInScreen
import com.gradation.lift.ui.DevicePreview

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    passwordText: String,
    updatePasswordText: (String) -> Unit,
) {
    Text(
        text = "이메일",
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = emailText,
        onValueChange = updateEmailText,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("이메일을 입력해주세요.") },
        singleLine = true,
    )
    Spacer(modifier = modifier.padding(8.dp))
    Text(
        text = "비밀번호",
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = passwordText,
        onValueChange = updatePasswordText,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("비밀번호를 입력해주세요.") },
        singleLine = true,
    )
    Spacer(modifier = modifier.padding(4.dp))

    SignInHelperView(modifier = modifier)

    Spacer(modifier = modifier.padding(36.dp))

    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = { },
    ) {
        Text(
            text = "로그인",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}


@Composable
fun SignInHelperView(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier=modifier.fillMaxWidth(),
        horizontalArrangement= Arrangement.SpaceBetween
        ){
        Text(
            text = "자동 로그인",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        FindIdPwdView()
    }
}

@Composable
fun FindIdPwdView(
    modifier: Modifier = Modifier,
){
    Row(
        modifier=modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "이메일 찾기",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = modifier.padding(2.dp))
        Divider(thickness = 1.dp, modifier= modifier
            .width(1.dp)
            .height(10.dp))
        Spacer(modifier = modifier.padding(2.dp))
        Text(
            text = "비밀번호 찾기",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}


@Composable
@DevicePreview
fun LoginSignInPreview() {
    LiftTheme {
        LoginSignInScreen(
            emailText = "",
            updateEmailText = {},
            passwordText = "",
            updatePasswordText = { },
        )
    }
}