@file:OptIn(ExperimentalMaterial3Api::class)

package com.gradation.lift.feature.login.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.component.*
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.feature.login.sign_in.component.SignUpView
import com.gradation.lift.feature.login.sign_in.data.LoginSignInViewModel
import com.gradation.lift.feature.login.sign_in.data.SignInState
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.oauth.state.OAuthConnectState
import com.gradation.lift.ui.utils.DevicePreview
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun LoginSignInRoute(
    navigateLoginGraphToMainGraph: () -> Unit,
    navigateSignInToSignUp: () -> Unit,
    navigateLoginGraphToRegisterDetailGraph: () -> Unit,
    navigateToLoginFindEmailPassword: () -> Unit,
    naverOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    connectOAuthFromNaver: () -> Unit,
    connectOAuthFromKakao: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val emailText: String by viewModel.emailText.collectAsStateWithLifecycle()
    val passwordText: String by viewModel.passwordText.collectAsStateWithLifecycle()
    val passwordVisibleToggle: Boolean by viewModel.passwordVisibleToggle.collectAsStateWithLifecycle()
    val autoLoginCheckToggle: Boolean by viewModel.autoLoginCheckToggle.collectAsStateWithLifecycle()
    val passwordVisualTransformation: VisualTransformation by viewModel.passwordVisualTransformation.collectAsStateWithLifecycle()

    val signInState: SignInState by viewModel.signInState.collectAsStateWithLifecycle()
    val naverConnectState: OAuthConnectState by naverOAuthConnectState.collectAsStateWithLifecycle()
    val kakaoConnectState: OAuthConnectState by kakaoOAuthConnectState.collectAsStateWithLifecycle()


    val updateEmailText: (String) -> Unit = viewModel.updateEmailText()
    val updatePasswordText: (String) -> Unit = viewModel.updatePasswordText()
    val clearPasswordText: () -> Unit = viewModel.clearPasswordText()

    val updatePasswordVisibleToggle: (Boolean) -> Unit = viewModel.updatePasswordVisibleToggle()
    val updateAutoLoginCheckToggle: (Boolean) -> Unit = viewModel.updateAutoLoginCheckToggle()
    val updateSignInState: (SignInState) -> Unit = viewModel.updateSignInState()

    val signInDefault: () -> Unit = viewModel.signInDefault()
    val signInKakao: () -> Unit = viewModel.signInKakao()
    val signInNaver: () -> Unit = viewModel.signInNaver()

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    LoginSignInScreen(
        modifier = modifier,
        emailText = emailText,
        passwordText = passwordText,
        passwordVisibleToggle = passwordVisibleToggle,
        autoLoginCheckToggle = autoLoginCheckToggle,
        passwordVisualTransformation = passwordVisualTransformation,


        updateEmailText = updateEmailText,
        updatePasswordText = updatePasswordText,
        clearPasswordText = clearPasswordText,
        updatePasswordVisibleToggle = updatePasswordVisibleToggle,
        updateAutoLoginCheckToggle = updateAutoLoginCheckToggle,

        signInDefault = signInDefault,
        signInNaver = connectOAuthFromNaver,
        signInKakao = connectOAuthFromKakao,


        signUp = navigateSignInToSignUp,
        findEmailPassword = navigateToLoginFindEmailPassword,

        snackbarHostState = snackbarHostState,
    )


    /**
     *  각 상태에 따른 처리 로직
     *  @since 2023-08-16 22:39:06
     */
    when (val naverConnectStateResult = naverConnectState) {
        is OAuthConnectState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = naverConnectStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignInState(SignInState.None)
            }
        }
        OAuthConnectState.None -> {}
        OAuthConnectState.Success -> {
            LaunchedEffect(true) {
                signInNaver()
            }
        }
    }
    when (val kakaoConnectStateResult = kakaoConnectState) {
        is OAuthConnectState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = kakaoConnectStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignInState(SignInState.None)
            }
        }
        OAuthConnectState.None -> {}
        OAuthConnectState.Success -> {
            LaunchedEffect(true){
                signInKakao()
            }
        }
    }
    when (val signInStateResult: SignInState = signInState) {
        is SignInState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = signInStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignInState(SignInState.None)
            }
        }
        SignInState.None -> {}
        is SignInState.Success -> {
            LaunchedEffect(signInStateResult) {
                if (signInStateResult.existUserDetail) {
                    navigateLoginGraphToMainGraph()
                } else {
                    navigateLoginGraphToRegisterDetailGraph()
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
    passwordVisibleToggle: Boolean,
    autoLoginCheckToggle: Boolean,
    passwordVisualTransformation: VisualTransformation,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    clearPasswordText: () -> Unit,
    updatePasswordVisibleToggle: (Boolean) -> Unit,
    updateAutoLoginCheckToggle: (Boolean) -> Unit,
    signInDefault: () -> Unit,
    signInNaver: () -> Unit,
    signInKakao: () -> Unit,
    signUp: () -> Unit,
    findEmailPassword: () -> Unit,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager = LocalFocusManager.current,
) {
    Scaffold(modifier = modifier, snackbarHost = {
        LiftErrorSnackBar(
            modifier=modifier,
            snackbarHostState = snackbarHostState
        )
    }) { it ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier
                .fillMaxSize()
                .padding(it)

        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                Spacer(modifier = modifier.padding(54.dp))
                HeaderView()
                Spacer(modifier = modifier.padding(12.dp))
                InputEmailTextView(
                    modifier,
                    emailText,
                    updateEmailText,
                    focusManager
                )
                Spacer(modifier = modifier.padding(8.dp))
                InputPasswordTextView(
                    modifier,
                    passwordText,
                    passwordVisibleToggle,
                    passwordVisualTransformation,
                    clearPasswordText,
                    updatePasswordText,
                    updatePasswordVisibleToggle,
                    signInDefault,
                    focusManager
                )
                Spacer(modifier = modifier.padding(4.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AutoLoginView(
                        modifier, autoLoginCheckToggle, updateAutoLoginCheckToggle
                    )
                }
                Spacer(modifier = modifier.padding(24.dp))


                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignInView(modifier, signInDefault, focusManager)
                    SignUpView(modifier, signUp, focusManager)

                }
                Spacer(modifier = modifier.padding(24.dp))


                SimpleLoginView(modifier, signInNaver, signInKakao)
            }
        }
    }

}


@Composable
@DevicePreview
internal fun LoginSignInPreview() {
    LiftMaterialTheme {
        LoginSignInScreen(
            emailText = "",
            passwordText = "",
            passwordVisibleToggle = true,
            autoLoginCheckToggle = true,
            passwordVisualTransformation = VisualTransformation.None,

            updateEmailText = {},
            updatePasswordText = {},
            clearPasswordText = {},
            updatePasswordVisibleToggle = {},
            updateAutoLoginCheckToggle = {},

            signInDefault = {},
            signInNaver = {},
            signInKakao = {},

            signUp = {},
            findEmailPassword = {},
            snackbarHostState = SnackbarHostState(),
        )
    }
}

