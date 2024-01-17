package com.gradation.lift.feature.login.verifyEmail.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.login.verifyEmail.data.EmailAuthenticationState
import com.gradation.lift.feature.login.verifyEmail.data.EmailValidationState
import com.gradation.lift.feature.login.verifyEmail.data.VerifyEmailScreenState
import com.gradation.lift.feature.login.verifyEmail.data.VerifyEmailState
import com.gradation.lift.feature.login.verifyEmail.data.VerifyEmailViewModel
import com.gradation.lift.feature.login.verifyEmail.data.rememberVerifyEmailScreenState
import com.gradation.lift.feature.login.verifyEmail.ui.VerifyEmailScreen
import kotlinx.datetime.LocalTime

@Composable
fun VerifyEmailRoute(
    modifier: Modifier = Modifier,
    navigateVerifyEmailToResetPasswordInLoginGraph: (String) -> Unit,
    navigateVerifyEmailToSignInDefaultInLoginGraph: () -> Unit,
    viewModel: VerifyEmailViewModel = hiltViewModel(),
    verifyEmailScreenState: VerifyEmailScreenState = rememberVerifyEmailScreenState(),
    verifyEmailState: VerifyEmailState = viewModel.verifyEmailState,
) {
    val emailAuthenticationState: EmailAuthenticationState by viewModel.emailAuthenticationState.collectAsStateWithLifecycle()
    val emailValidationState: EmailValidationState by viewModel.emailValidationState.collectAsStateWithLifecycle()

    val email: String by verifyEmailState.email.collectAsStateWithLifecycle()
    val emailAuthenticationCode: String by verifyEmailState.emailAuthenticationCode.collectAsStateWithLifecycle()
    val authenticationTimer: LocalTime by verifyEmailState.authenticationTimer.collectAsStateWithLifecycle()
    val emailValidator: Validator by verifyEmailState.emailValidator.collectAsStateWithLifecycle()
    val authenticationCodeValidator: Validator by verifyEmailState.authenticationValidator.collectAsStateWithLifecycle()

    val updateEmailAuthenticationState: (EmailAuthenticationState) -> Unit =
        viewModel.updateEmailAuthenticationState

    val updateEmailValidationState: (EmailValidationState) -> Unit =
        viewModel.updateEmailValidationState

    val createEmailAuthenticationCode: (String) -> Unit = viewModel.createEmailAuthenticationCode
    val validateEmailAuthentication: (String, Int?) -> Unit = viewModel.validateEmailAuthentication


    when (val result: EmailAuthenticationState = emailAuthenticationState) {
        is EmailAuthenticationState.Fail -> {
            LaunchedEffect(result.message) {
                verifyEmailScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Long
                )
                updateEmailAuthenticationState(EmailAuthenticationState.None)
            }
        }

        EmailAuthenticationState.None -> {}
        is EmailAuthenticationState.Success -> {
            if (!result.isSuccess) {
                LaunchedEffect(result) {
                    verifyEmailScreenState.snackbarHostState.showSnackbar(
                        message = "존재하지 않는 이메일 입니다.", duration = SnackbarDuration.Long
                    )
                    updateEmailAuthenticationState(EmailAuthenticationState.None)
                }
            } else {
                verifyEmailScreenState.updateAuthenticationCodeTextFieldView(true)
                verifyEmailScreenState.updateAuthenticationButtonView(true)
            }
        }

        EmailAuthenticationState.Loading -> {}
    }

    when (val result = emailValidationState) {
        is EmailValidationState.Fail -> {
            LaunchedEffect(result.message) {
                verifyEmailScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Long
                )
                updateEmailValidationState(EmailValidationState.None)
            }
        }

        EmailValidationState.None -> {}
        is EmailValidationState.Success -> {
            if (!result.isSuccess) {
                LaunchedEffect(true) {
                    verifyEmailScreenState.snackbarHostState.showSnackbar(
                        message = "인증에 실패하였습니다.", duration = SnackbarDuration.Long
                    )
                    updateEmailValidationState(EmailValidationState.None)
                }
            } else {
                navigateVerifyEmailToResetPasswordInLoginGraph(email)
            }
        }
    }


    BackHandler(onBack = navigateVerifyEmailToSignInDefaultInLoginGraph)


    VerifyEmailScreen(
        modifier,
        email,
        emailAuthenticationCode,
        authenticationTimer,
        emailValidator,
        authenticationCodeValidator,
        createEmailAuthenticationCode,
        validateEmailAuthentication,
        navigateVerifyEmailToSignInDefaultInLoginGraph,
        verifyEmailState,
        emailAuthenticationState,
        verifyEmailScreenState
    )
}
