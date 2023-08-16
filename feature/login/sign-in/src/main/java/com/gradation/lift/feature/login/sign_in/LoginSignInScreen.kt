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
import com.gradation.lift.domain.usecase.auth.SignInKakaoUseCase
import com.gradation.lift.domain.usecase.auth.SignInNaverUseCase
import com.gradation.lift.feature.login.sign_in.component.SignInView
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.feature.login.sign_in.data.LoginSignInViewModel
import com.gradation.lift.feature.login.sign_in.data.SignInState
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.oauth.state.OAuthSignInState
import com.gradation.lift.ui.utils.DevicePreview
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@Composable
internal fun LoginSignInRoute(
    navigateToLoginFindEmailPassword: () -> Unit,
    navigateToLoginSignUp: () -> Unit,
    navigateLoginToHome: () -> Unit,
    navigateLoginToRegisterDetail: () -> Unit,
    naverOAuthSignInState: OAuthSignInState,
    kakaoOauthSignInState: OAuthSignInState,
    signInNaver: ()->Unit,
    signInKakao: ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {



    val signInState: SignInState by viewModel.signInUiState.collectAsStateWithLifecycle()
    val autoLoginChecked by viewModel.autoLoginChecked.collectAsStateWithLifecycle()

    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordVisible by viewModel.passwordVisible.collectAsStateWithLifecycle()
    val passwordVisualTransformation by viewModel.passwordVisualTransformation.collectAsStateWithLifecycle()

    val updateEmail = viewModel.updateEmail()
    val updatePassword = viewModel.updatePassword()
    val changeAutoLoginChecked = viewModel.changeAutoLoginChecked()
    val changePasswordVisible = viewModel.changePasswordVisible()
    val clearPassword = viewModel.clearPassword()
    val signIn = viewModel.signIn()
//    val signInNaver = viewModel.signInNaver()
//    val signInKakao = viewModel.signInKakao()


    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    LoginSignInScreen(
        modifier = modifier,
        emailText = email,
        passwordText = password,
        updateEmailText = updateEmail,
        updatePasswordText = updatePassword,
        onClickFindEmailPassword = navigateToLoginFindEmailPassword,
        onClickSignUp = navigateToLoginSignUp,
        onClickSignIn = signIn,
        onClickSignInNaver = signInNaver,
        onClickSignInKakao = signInKakao,
        autoLoginChecked = autoLoginChecked,
        onChangeAutoLoginChecked = changeAutoLoginChecked,
        passwordVisible = passwordVisible,
        onChangePasswordVisible = changePasswordVisible,
        passwordVisualTransformation = passwordVisualTransformation,
        clearPassword = clearPassword,
        snackbarHostState = snackbarHostState
    )


    when (val result = signInState) {
        is SignInState.Fail -> {
            LaunchedEffect(result.message) {
                snackbarHostState.showSnackbar(
                    message = result.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
        SignInState.None -> {}
        is SignInState.Success -> {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginSignInScreen(
    modifier: Modifier = Modifier,
    emailText: String,
    passwordText: String,
    autoLoginChecked: Boolean,
    passwordVisible: Boolean,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    onClickFindEmailPassword: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickSignIn: () -> Unit,
    onClickSignInNaver: () -> Unit,
    onClickSignInKakao: () -> Unit,
    onChangeAutoLoginChecked: (Boolean) -> Unit,
    onChangePasswordVisible: (Boolean) -> Unit,
    clearPassword: () -> Unit,
    passwordVisualTransformation: VisualTransformation,
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
                onClickFindEmailPassword = onClickFindEmailPassword,
                onClickSignUp = onClickSignUp,
                onClickSignIn = onClickSignIn,
                autoLoginChecked = autoLoginChecked,
                onChangeAutoLoginChecked = onChangeAutoLoginChecked,
                passwordVisible = passwordVisible,
                onChangePasswordVisible = onChangePasswordVisible,
                passwordVisualTransformation = passwordVisualTransformation,
                clearPassword = clearPassword,

                )
            Spacer(modifier = modifier.padding(18.dp))
            SimpleLoginView(
                onClickSignInNaver = onClickSignInNaver,
                onClickSignInKakao = onClickSignInKakao
            )
        }
    }

}


@Composable
@DevicePreview
internal fun LoginSignInPreview() {
    LiftMaterialTheme {
        LoginSignInScreen(
            emailText = "",
            updateEmailText = {},
            passwordText = "",
            updatePasswordText = {},
            onClickFindEmailPassword = {},
            onClickSignUp = {},
            onClickSignIn = {},
            onClickSignInNaver = { },
            onClickSignInKakao = { },
            autoLoginChecked = true,
            onChangeAutoLoginChecked = {},
            passwordVisible = true,
            onChangePasswordVisible = {},
            passwordVisualTransformation = VisualTransformation.None,
            clearPassword = {},
            snackbarHostState = SnackbarHostState(),

            )
    }
}

