package com.gradation.lift.feature.login.sign_up

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_up.component.EmailTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordVerificationTextField
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.navigation.navigation.navigateToLoginTermsOfUse
import com.gradation.lift.ui.DevicePreview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun LoginSignUpRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignUpViewModel = hiltViewModel(),
) {

    val email = viewModel.email.collectAsStateWithLifecycle()
    val password = viewModel.password.collectAsStateWithLifecycle()
    val passwordVerification = viewModel.passwordVerification.collectAsStateWithLifecycle()

    val passwordVisible = viewModel.passwordVisible.collectAsStateWithLifecycle()
    val passwordVerificationVisible = viewModel.passwordVerificationVisible.collectAsStateWithLifecycle()

    val passwordVisualTransformation =  viewModel.passwordVisualTransformation.collectAsStateWithLifecycle()
    val passwordVerificationVisualTransformation =  viewModel.passwordVerificationVisualTransformation.collectAsStateWithLifecycle()

    val emailValidator =  viewModel.emailValidator.collectAsStateWithLifecycle()
    val passwordValidator =  viewModel.passwordValidator.collectAsStateWithLifecycle()
    val passwordVerificationValidator =  viewModel.passwordVerificationValidator.collectAsStateWithLifecycle()

    val navigateCondition = viewModel.navigateCondition.collectAsStateWithLifecycle()

    LoginSignUpScreen(
        modifier = modifier,
        onBackClickTopbar = { navController.navigateSignUpProcessToSignIn() },

        emailText = email,
        passwordText = password,
        passwordVerificationText = passwordVerification,

        updateEmailText = viewModel.updateEmail(),
        updatePasswordText = viewModel.updatePassword(),
        updatePasswordVerificationText = viewModel.updatePasswordVerification(),

        passwordVisible = passwordVisible,
        passwordVerificationVisible = passwordVerificationVisible,

        passwordVisualTransformation = passwordVisualTransformation,
        passwordVerificationVisualTransformation = passwordVerificationVisualTransformation,

        clearPassword = viewModel.clearPassword(),
        clearPasswordVerification = viewModel.clearPasswordVerification(),

        onChangePasswordVisible = viewModel.updatePasswordVisible(),
        onChangePasswordVerificationVisible = viewModel.updatePasswordVerificationVisible(),

        emailValidationSupportText = emailValidator,
        passwordValidationSupportText = passwordValidator,
        passwordVerificationValidationSupportText = passwordVerificationValidator,

        onNextButtonClick = {
            viewModel.updateKey(navController)
            navController.navigateToLoginTermsOfUse()
        },
        navigateCondition = navigateCondition
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginSignUpScreen(
    modifier: Modifier = Modifier,
    onBackClickTopbar: () -> Unit,
    emailText: State<String>,
    passwordText: State<String>,
    passwordVerificationText: State<String>,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    updatePasswordVerificationText: (String) -> Unit,
    passwordVisible: State<Boolean>,
    passwordVerificationVisible: State<Boolean>,
    passwordVisualTransformation: State<VisualTransformation>,
    passwordVerificationVisualTransformation: State<VisualTransformation>,
    clearPassword: () -> Unit,
    clearPasswordVerification: () -> Unit,
    onChangePasswordVisible: (Boolean) -> Unit,
    onChangePasswordVerificationVisible: (Boolean) -> Unit,
    emailValidationSupportText: State<Validator>,
    passwordValidationSupportText: State<Validator>,
    passwordVerificationValidationSupportText: State<Validator>,
    onNextButtonClick: () -> Unit,
    navigateCondition: State<Boolean>,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "회원가입",
                    onBackClickTopbar = onBackClickTopbar,
                )
            },
        ) { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                val focusManager = LocalFocusManager.current
                EmailTextField(
                    modifier = modifier,
                    emailText = emailText,
                    updateEmailText = updateEmailText,
                    focusManager = focusManager,
                    emailValidationSupportText = emailValidationSupportText,
                )
                Spacer(modifier = modifier.padding(18.dp))
                PasswordTextField(
                    modifier = modifier,
                    passwordText = passwordText,
                    updatePasswordText = updatePasswordText,
                    focusManager = focusManager,
                    passwordVisualTransformation = passwordVisualTransformation,
                    passwordVisible = passwordVisible,
                    onChangePasswordVisible = onChangePasswordVisible,
                    clearPassword = clearPassword,
                    passwordValidationSupportText = passwordValidationSupportText
                )
                Spacer(modifier = modifier.padding(18.dp))
                PasswordVerificationTextField(
                    modifier = modifier,
                    passwordVerificationText = passwordVerificationText,
                    updatePasswordVerificationText = updatePasswordVerificationText,
                    focusManager = focusManager,
                    passwordVerificationVisualTransformation = passwordVerificationVisualTransformation,
                    passwordVerificationVisible = passwordVerificationVisible,
                    onChangePasswordVerificationVisible = onChangePasswordVerificationVisible,
                    clearPasswordVerification = clearPasswordVerification,
                    passwordVerificationValidationSupportText = passwordVerificationValidationSupportText
                )
                Spacer(modifier = modifier.padding(36.dp))

                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onNextButtonClick,
                    enabled = navigateCondition.value
                ) {
                    Text(
                        text = "다음",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }

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
            onBackClickTopbar = { },
            emailText = mutableStateOf("") ,
            passwordText = mutableStateOf(""),
            passwordVerificationText = mutableStateOf(""),
            updateEmailText = {},
            updatePasswordText = {},
            updatePasswordVerificationText = { },
            passwordVisible = mutableStateOf(true),
            passwordVerificationVisible = mutableStateOf(true),
            passwordVisualTransformation = mutableStateOf(VisualTransformation.None),
            passwordVerificationVisualTransformation = mutableStateOf(VisualTransformation.None),
            clearPassword = { },
            clearPasswordVerification = { },
            onChangePasswordVisible = { },
            onChangePasswordVerificationVisible = {},
            emailValidationSupportText = mutableStateOf(Validator(status = false, message = "실패")),
            passwordValidationSupportText = mutableStateOf(Validator(status = false, message = "실패")),
            passwordVerificationValidationSupportText = mutableStateOf(Validator(status = false, message = "실패")),
            onNextButtonClick = {},
            navigateCondition = mutableStateOf(true)
        )
    }
}