package com.gradation.lift.feature.login.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_up.component.EmailTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordTextField
import com.gradation.lift.feature.login.sign_up.component.PasswordVerificationTextField
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.navigation.navigation.navigateToLoginTermsOfUse
import com.gradation.lift.navigation.navigation.navigateToLoginVerification
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginSignUpRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignUpViewModel = hiltViewModel(),
) {


    LoginSignUpScreen(
        modifier = modifier,
        onTopBarBackClick = { navController.navigateSignUpProcessToSignIn() },

        emailText = viewModel.email,
        passwordText = viewModel.password,
        passwordVerificationText = viewModel.passwordVerification,

        updateEmailText = viewModel.updateEmail(),
        updatePasswordText = viewModel.updatePassword(),
        updatePasswordVerificationText = viewModel.updatePasswordVerification(),

        passwordVisible = viewModel.passwordVisible,
        passwordVerificationVisible = viewModel.passwordVerificationVisible,

        passwordVisualTransformation = viewModel.passwordVisualTransformation,
        passwordVerificationVisualTransformation = viewModel.passwordVerificationVisualTransformation,

        clearPassword = viewModel.clearPassword(),
        clearPasswordVerification = viewModel.clearPasswordVerification(),

        onChangePasswordVisible = viewModel.onChangePasswordVisible(),
        onChangePasswordVerificationVisible = viewModel.onChangePasswordVerificationVisible(),

        emailValidationSupportText = viewModel.emailValidationSupportText,
        passwordValidationSupportText = viewModel.passwordValidationSupportText,
        passwordVerificationValidationSupportText = viewModel.passwordVerificationValidationSupportText,

        onNextButtonClick = {
            viewModel.updateKey(navController)
            navController.navigateToLoginTermsOfUse()
        },
        navigateCondition = viewModel.navigateCondition
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginSignUpScreen(
    modifier: Modifier = Modifier,
    onTopBarBackClick: () -> Unit,
    emailText: String,
    passwordText: String,
    passwordVerificationText: String,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    updatePasswordVerificationText: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVerificationVisible: Boolean,
    passwordVisualTransformation: VisualTransformation,
    passwordVerificationVisualTransformation: VisualTransformation,
    clearPassword: () -> Unit,
    clearPasswordVerification: () -> Unit,
    onChangePasswordVisible: (Boolean) -> Unit,
    onChangePasswordVerificationVisible: (Boolean) -> Unit,
    emailValidationSupportText: Validator,
    passwordValidationSupportText: Validator,
    passwordVerificationValidationSupportText: Validator,
    onNextButtonClick: () -> Unit,
    navigateCondition: Boolean,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "회원가입",
                    onBackClick = onTopBarBackClick,
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
                    enabled = navigateCondition
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


@DevicePreview
@Composable
fun LoginSignInPreview() {
    LiftMaterialTheme {
        LoginSignUpScreen(
            modifier = Modifier,
            onTopBarBackClick = { },
            emailText = "",
            passwordText = "",
            passwordVerificationText = "",
            updateEmailText = {},
            updatePasswordText = {},
            updatePasswordVerificationText = { },
            passwordVisible = true,
            passwordVerificationVisible = true,
            passwordVisualTransformation = VisualTransformation.None,
            passwordVerificationVisualTransformation = VisualTransformation.None,
            clearPassword = { },
            clearPasswordVerification = { },
            onChangePasswordVisible = { },
            onChangePasswordVerificationVisible = {},
            emailValidationSupportText = Validator(status = false, message = "실패"),
            passwordValidationSupportText = Validator(status = false, message = "실패"),
            passwordVerificationValidationSupportText = Validator(status = false, message = "실패"),
            onNextButtonClick = {},
            navigateCondition = true
        )
    }
}