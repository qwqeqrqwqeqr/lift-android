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
import com.gradation.lift.feature.login.sign_in.component.SignInView
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.navigation.navigation.navigateToFindEmail
import com.gradation.lift.navigation.navigation.navigateToFindPassword
import com.gradation.lift.navigation.navigation.navigateToLoginSignUp
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val emailText = viewModel.email
    val passwordText = viewModel.password
    val updateEmailText = viewModel::updateEmail
    val updatePasswordText = viewModel::updatePassword



    LoginSignInScreen(
        modifier = modifier,
        emailText = emailText,
        passwordText = passwordText,
        updateEmailText = updateEmailText,
        updatePasswordText = updatePasswordText,
        onClickFindEmail = { navController.navigateToFindEmail() },
        onClickFindPassword = { navController.navigateToFindPassword() },
        onClickSignUp = { navController.navigateToLoginSignUp() }
    )
}


@Composable
fun LoginSignInScreen(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    passwordText: String,
    updatePasswordText: (String) -> Unit,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
    onClickSignUp: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(32.dp))
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
                emailText = emailText,
                updateEmailText = updateEmailText,
                passwordText = passwordText,
                updatePasswordText = updatePasswordText,
                onClickFindEmail = onClickFindEmail,
                onClickFindPassword = onClickFindPassword,
                onClickSignUp=onClickSignUp
            )
            Spacer(modifier = modifier.padding(32.dp))
            SimpleLoginView()
        }
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
            onClickFindEmail = {},
            onClickFindPassword = {},
            onClickSignUp={}
        )
    }
}