package com.gradation.lift.feature.login.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.component.SignInView
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val signInUiState: SignInUiState by viewModel.signInUiState.collectAsStateWithLifecycle()

    LoginSignInScreen(
        modifier = modifier,
        emailText = viewModel.email,
        passwordText = viewModel.password,
        updateEmailText =  viewModel.updateEmail(),
        updatePasswordText = viewModel.updatePassword(),
        onClickFindEmail = { navController.navigateToLoginFindEmail() },
        onClickFindPassword = { navController.navigateToLoginFindPassword() },
        onClickSignUp = { navController.navigateToLoginSignUp() },
        onClickSignIn = viewModel::signIn,
        autoLoginChecked = viewModel.autoLoginChecked,
        onChangeAutoLoginChecked = { viewModel.onChangeAutoLoginChecked() },
        passwordVisible = viewModel.passwordVisible,
        onChangePasswordVisible = { viewModel.onChangePasswordVisible() },
        passwordVisualTransformation = viewModel.passwordVisualTransformation,
        clearPassword = viewModel.clearPassword()
    )

    when (val result = signInUiState) {
        is SignInUiState.Fail -> {
            Toast.makeText(
                LocalContext.current,
                result.message,
                Toast.LENGTH_SHORT
            ).show()
            //TODO 삭제 예정
        }
        SignInUiState.Loading -> {


        }
        SignInUiState.None -> {

        }
        SignInUiState.Success -> {
            LaunchedEffect(true) {
                navController.navigateLoginToHome()
            }
        }
    }
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
    onClickSignIn: () -> Unit,
    autoLoginChecked: Boolean,
    onChangeAutoLoginChecked: (Boolean) -> Unit,
    passwordVisible: Boolean,
    onChangePasswordVisible: (Boolean) -> Unit,
    passwordVisualTransformation: VisualTransformation,
    clearPassword: () -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Column(
            modifier = modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {

            Spacer(modifier = modifier.padding(32.dp))
            Text(
                text = buildAnnotatedString {
                    append("매일매일 운동하고, 기록하고! \n")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no4),
                    ) {
                        append("나만의 운동파트너, 리프트")
                    }
                },
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no11,
            )
            Spacer(modifier = modifier.padding(24.dp))
            SignInView(
                emailText = emailText,
                updateEmailText = updateEmailText,
                passwordText = passwordText,
                updatePasswordText = updatePasswordText,
                onClickFindEmail = onClickFindEmail,
                onClickFindPassword = onClickFindPassword,
                onClickSignUp = onClickSignUp,
                onClickSignIn = onClickSignIn,
                autoLoginChecked = autoLoginChecked,
                onChangeAutoLoginChecked = onChangeAutoLoginChecked,
                passwordVisible = passwordVisible,
                onChangePasswordVisible = onChangePasswordVisible,
                passwordVisualTransformation = passwordVisualTransformation,
                clearPassword = clearPassword
            )
            Spacer(modifier = modifier.padding(32.dp))
            SimpleLoginView()


        }
    }
}


@Composable
@Preview
fun LoginSignInPreview() {
    LiftMaterialTheme {
        LoginSignInScreen(
            emailText = "",
            updateEmailText = {},
            passwordText = "",
            updatePasswordText = {},
            onClickFindEmail = {},
            onClickFindPassword = {},
            onClickSignUp = {},
            onClickSignIn = {},
            autoLoginChecked = true,
            onChangeAutoLoginChecked = {},
            passwordVisible = true,
            onChangePasswordVisible = {},
            passwordVisualTransformation = VisualTransformation.None,
            clearPassword = {}
        )
    }
}

