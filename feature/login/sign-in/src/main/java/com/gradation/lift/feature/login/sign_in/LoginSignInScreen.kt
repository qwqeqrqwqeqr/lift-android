@file:OptIn(ExperimentalMaterial3Api::class)

package com.gradation.lift.feature.login.sign_in

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftErrorSnackbar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.component.SignInView
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.ui.utils.DevicePreview

@Composable
internal fun LoginSignInRoute(
    navigateToLoginFindEmail: () -> Unit,
    navigateToLoginFindPassword: () -> Unit,
    navigateToLoginSignUp: () -> Unit,
    navigateLoginToHome: () -> Unit,
    navigateLoginToRegisterDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val signInUiState: SignInUiState by viewModel.signInUiState.collectAsStateWithLifecycle()
    val autoLoginChecked = viewModel.autoLoginChecked.collectAsStateWithLifecycle()

    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
    LoginSignInScreen(
        modifier = modifier,
        emailText = viewModel.email,
        passwordText = viewModel.password,
        updateEmailText = viewModel.updateEmail(),
        updatePasswordText = viewModel.updatePassword(),
        onClickFindEmail = navigateToLoginFindEmail,
        onClickFindPassword = navigateToLoginFindPassword,
        onClickSignUp = navigateToLoginSignUp,
        onClickSignIn = viewModel.signIn(),
        autoLoginChecked = autoLoginChecked.value,
        onChangeAutoLoginChecked = viewModel.onChangeAutoLoginChecked(),
        passwordVisible = viewModel.passwordVisible,
        onChangePasswordVisible = viewModel.onChangePasswordVisible(),
        passwordVisualTransformation = viewModel.passwordVisualTransformation,
        clearPassword = viewModel.clearPassword(),
        snackbarHostState = snackbarHostState.value
    )

    when (val result = signInUiState) {
        is SignInUiState.Fail -> {
            //TODO 클릭시 마다 실행 되도록 구현할 것
            LaunchedEffect(result.message) {
                snackbarHostState.value.showSnackbar(
                    message = result.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
        SignInUiState.None -> {}
        is SignInUiState.Success -> {
            LaunchedEffect(result) {
                if (result.existUserDetail) {
                     navigateLoginToHome()
                } else {
                    navigateLoginToRegisterDetail()
                }

            }
        }
    }
}


@Composable
internal fun LoginSignInScreen(
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
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = modifier.padding(16.dp),
        snackbarHost = {
            LiftErrorSnackbar(snackbarHostState = snackbarHostState)
        }
    ) { it ->
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
        ) {
            Spacer(modifier = modifier.padding(48.dp))
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
                lineHeight = 30.sp,
                color = LiftTheme.colorScheme.no11,
            )
            Spacer(modifier = modifier.padding(12.dp))
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
            Spacer(modifier = modifier.padding(18.dp))
            SimpleLoginView()
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Composable
@DevicePreview
internal fun LoginSignInPreview() {
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
            clearPassword = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}

