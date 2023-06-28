package com.gradation.lift.feature.login.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val emailText = viewModel.email
    val passwordText = viewModel.email
    val updateEmailText = viewModel::updateEmail
    val updatePasswordText = viewModel::updatePassword

    LoginSignInScreen(
        emailText =emailText,
        passwordText = passwordText,
        updateEmailText = updateEmailText,
        updatePasswordText = updatePasswordText
    )
}


@Composable
fun LoginSignInScreen(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    passwordText: String,
    updatePasswordText: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(48.dp))
            Text(
                text = buildAnnotatedString {
                    append("매일매일 운동하고, 기록하고! \n")
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    ) {
                        append("나만의 운동파트너, 리프트")
                    }
                },
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = modifier.padding(24.dp))
            SignInView(
                emailText=emailText,
                updateEmailText=updateEmailText,
                passwordText=passwordText,
                updatePasswordText = updatePasswordText,
            )
            Spacer(modifier = modifier.padding(16.dp))

            SimpleLoginView()

        }
    }

}


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
fun SimpleLoginView(modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
        Text(
            text = "간편하게 로그인",
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier.weight(1f),
            textAlign = TextAlign.Center

        )
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
    }
    Spacer(modifier = modifier.padding(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp)
    ) {
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginKakao),
            label = "카카오톡",
            modifier = modifier.weight(2f)
        )
        Spacer(modifier = modifier.weight(1f))
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginGoogle),
            label = "구글",
            modifier = modifier.weight(2f)
        )
        Spacer(modifier = modifier.weight(1f))
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginNaver),
            label = "네이버",
            modifier = modifier.weight(2f)
        )
    }
}

@Composable
fun SimpleLoginButton(
    painterResource: Painter,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = modifier.size(52.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
        )
    }

}

@Composable
@Preview
fun LoginSignInPreview() {
    LiftTheme {
        LoginSignInScreen(
            emailText="",
            updateEmailText= {},
            passwordText="",
            updatePasswordText = { },
        )
    }
}