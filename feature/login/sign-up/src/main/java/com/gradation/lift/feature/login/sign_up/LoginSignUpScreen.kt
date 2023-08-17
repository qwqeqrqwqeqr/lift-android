package com.gradation.lift.feature.login.sign_up

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_up.component.EmailTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordVerificationTextField
import com.gradation.lift.feature.login.sign_up.component.SignUpView
import com.gradation.lift.feature.login.sign_up.data.SignUpState
import com.gradation.lift.ui.utils.DevicePreview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun LoginSignUpRoute(
    navigateSignUpToComplete: () -> Unit,
    navigateSignUpToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginSignUpViewModel = hiltViewModel(),
) {

    val emailText: String by viewModel.emailText.collectAsStateWithLifecycle()
    val passwordText: String by viewModel.passwordText.collectAsStateWithLifecycle()
    val passwordVerificationText: String by viewModel.passwordVerificationText.collectAsStateWithLifecycle()

    val passwordVisibleToggle: Boolean by viewModel.passwordVisibleToggle.collectAsStateWithLifecycle()
    val passwordVerificationVisibleToggle: Boolean by viewModel.passwordVerificationVisibleToggle.collectAsStateWithLifecycle()

    val emailValidator: Validator by viewModel.emailValidator.collectAsStateWithLifecycle()
    val passwordValidator: Validator by viewModel.passwordValidator.collectAsStateWithLifecycle()
    val passwordVerificationValidator: Validator by viewModel.passwordVerificationValidator.collectAsStateWithLifecycle()

    val passwordVisualTransformation: VisualTransformation by viewModel.passwordVisualTransformation.collectAsStateWithLifecycle()
    val passwordVerificationVisualTransformation: VisualTransformation by viewModel.passwordVerificationVisualTransformation.collectAsStateWithLifecycle()

    val signUpCondition: Boolean by viewModel.signUpCondition.collectAsStateWithLifecycle()
    val signUpState: SignUpState by viewModel.signUpState.collectAsStateWithLifecycle()


    val updateEmailText: (String) -> Unit = viewModel.updateEmailText()
    val updatePasswordText: (String) -> Unit = viewModel.updatePasswordText()
    val updatePasswordVerificationText: (String) -> Unit =
        viewModel.updatePasswordVerificationText()

    val clearPasswordText: () -> Unit = viewModel.clearPasswordText()
    val clearPasswordVerificationText: () -> Unit = viewModel.clearPasswordVerificationText()

    val updatePasswordVisibleToggle: (Boolean) -> Unit = viewModel.updatePasswordVisibleToggle()
    val updatePasswordVerificationVisibleToggle: (Boolean) -> Unit =
        viewModel.updatePasswordVerificationVisibleToggle()

    val updateSignUpState: (SignUpState) -> Unit = viewModel.updateSignUpState()
    val signUp: () -> Unit = viewModel.signUp()

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

    LoginSignUpScreen(
        modifier,
        emailText,
        passwordText,
        passwordVerificationText,
        passwordVisibleToggle,
        passwordVerificationVisibleToggle,
        emailValidator,
        passwordValidator,
        passwordVerificationValidator,
        passwordVisualTransformation,
        passwordVerificationVisualTransformation,
        signUpCondition,
        updateEmailText,
        updatePasswordText,
        updatePasswordVerificationText,
        clearPasswordText,
        clearPasswordVerificationText,
        updatePasswordVisibleToggle,
        updatePasswordVerificationVisibleToggle,
        signUp,
        navigateSignUpToSignIn,
        snackbarHostState,
        focusManager
    )


    when (val signUpStateResult = signUpState) {
        is SignUpState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = signUpStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignUpState(SignUpState.None)
            }
        }
        SignUpState.None -> {}
        is SignUpState.Success -> {
            LaunchedEffect(true) {
                navigateSignUpToComplete()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginSignUpScreen(
    modifier: Modifier = Modifier,
    emailText: String,
    passwordText: String,
    passwordVerificationText: String,
    passwordVisibleToggle: Boolean,
    passwordVerificationVisibleToggle: Boolean,
    emailValidator: Validator,
    passwordValidator: Validator,
    passwordVerificationValidator: Validator,
    passwordVisualTransformation: VisualTransformation,
    passwordVerificationVisualTransformation: VisualTransformation,
    signUpCondition: Boolean,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    updatePasswordVerificationText: (String) -> Unit,
    clearPasswordText: () -> Unit,
    clearPasswordVerificationText: () -> Unit,
    updatePasswordVisibleToggle: (Boolean) -> Unit,
    updatePasswordVerificationVisibleToggle: (Boolean) -> Unit,
    signUp: () -> Unit,
    navigateSignUpToSignIn: () -> Unit,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "회원가입",
                onBackClickTopBar = navigateSignUpToSignIn,
            )
        },
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no5,
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                Spacer(modifier = modifier.padding(24.dp))
                EmailTextField(
                    modifier,
                    emailText,
                    emailValidator,
                    updateEmailText,
                    focusManager
                )
                Spacer(modifier = modifier.padding(18.dp))
                PasswordTextField(
                    modifier,
                    passwordText,
                    passwordVisibleToggle,
                    passwordValidator,
                    passwordVisualTransformation,
                    updatePasswordText,
                    clearPasswordText,
                    updatePasswordVisibleToggle,
                    focusManager
                )
                Spacer(modifier = modifier.padding(18.dp))
                PasswordVerificationTextField(
                    modifier,
                    passwordVerificationText,
                    passwordVerificationVisibleToggle,
                    passwordVerificationValidator,
                    passwordVerificationVisualTransformation,
                    updatePasswordVerificationText,
                    updatePasswordVerificationVisibleToggle,
                    clearPasswordVerificationText,
                    focusManager
                )
                Spacer(modifier = modifier.padding(18.dp))
                SignUpView(modifier, signUpCondition, signUp)
            }
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@DevicePreview
@Composable
internal fun LoginSignInUpPreview() {
    LiftMaterialTheme {
        LoginSignUpScreen(
            modifier = Modifier,
            emailText = "",
            passwordText = "",
            passwordVerificationText = "",
            passwordVisibleToggle = false,
            passwordVerificationVisibleToggle = false,
            emailValidator = Validator(status = false, message = "실패"),
            passwordValidator = Validator(status = false, message = "실패"),
            passwordVerificationValidator = Validator(status = false, message = "실패"),
            passwordVisualTransformation = VisualTransformation.None,
            passwordVerificationVisualTransformation = VisualTransformation.None,
            signUpCondition = true,
            updateEmailText = {},
            updatePasswordText = {},
            updatePasswordVerificationText = {},
            clearPasswordText = {},
            clearPasswordVerificationText = {},
            updatePasswordVisibleToggle = {},
            updatePasswordVerificationVisibleToggle = {},
            signUp = {},
            navigateSignUpToSignIn = {},
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}

