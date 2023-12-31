package com.gradation.lift.feature.login.signUpCreateEmail.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.common.data.LoginSharedViewModel
import com.gradation.lift.feature.login.signUpCreateEmail.data.CreateEmailState
import com.gradation.lift.feature.login.signUpCreateEmail.data.EmailAuthenticationState
import com.gradation.lift.feature.login.signUpCreateEmail.data.EmailValidationState
import com.gradation.lift.feature.login.signUpCreateEmail.data.SignUpCreateEmailScreenState
import com.gradation.lift.feature.login.signUpCreateEmail.data.SignUpCreateEmailViewModel
import com.gradation.lift.feature.login.signUpCreateEmail.data.rememberSignUpCreateEmailScreenState
import com.gradation.lift.feature.login.signUpCreateEmail.ui.SignUpCreateEmailScreen
import com.gradation.lift.navigation.Router
import kotlinx.datetime.LocalTime

@Composable
fun SignUpCreateEmailRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph: () -> Unit,
    navigateSignUpToSignInInLoginGraph: () -> Unit,
    viewModel: SignUpCreateEmailViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Router.LOGIN_GRAPH_NAME) }
    ),
    signUpCreateEmailScreenState: SignUpCreateEmailScreenState = rememberSignUpCreateEmailScreenState(),
    createEmailState: CreateEmailState = viewModel.createEmailState,
) {
    val emailAuthenticationState: EmailAuthenticationState by viewModel.emailAuthenticationState.collectAsStateWithLifecycle()
    val emailValidationState: EmailValidationState by viewModel.emailValidationState.collectAsStateWithLifecycle()

    val email: String by createEmailState.email.collectAsStateWithLifecycle()
    val emailAuthenticationCode: String by createEmailState.emailAuthenticationCode.collectAsStateWithLifecycle()
    val authenticationTimer: LocalTime by createEmailState.authenticationTimer.collectAsStateWithLifecycle()
    val emailValidator: Validator by createEmailState.emailValidator.collectAsStateWithLifecycle()
    val authenticationCodeValidator: Validator by createEmailState.authenticationValidator.collectAsStateWithLifecycle()


    val updateEmailAuthenticationState: (EmailAuthenticationState) -> Unit =
        viewModel.updateEmailAuthenticationState

    val updateEmailValidationState: (EmailValidationState) -> Unit =
        viewModel.updateEmailValidationState

    val updateCurrentLoginMethodState: (LoginMethodState) -> Unit =
        sharedViewModel.updateCurrentLoginMethodState

    val createEmailAuthenticationCode: (String) -> Unit = viewModel.createEmailAuthenticationCode
    val validateEmailAuthentication: (String, Int?) -> Unit = viewModel.validateEmailAuthentication


    when (val result: EmailAuthenticationState = emailAuthenticationState) {
        is EmailAuthenticationState.Fail -> {
            LaunchedEffect(result.message) {
                signUpCreateEmailScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Long
                )
                updateEmailAuthenticationState(EmailAuthenticationState.None)
            }
        }

        EmailAuthenticationState.None -> {}
        is EmailAuthenticationState.Success -> {
            if (!result.isSuccess) {
                LaunchedEffect(result) {
                    signUpCreateEmailScreenState.snackbarHostState.showSnackbar(
                        message = "이미 사용중인 이메일 입니다.", duration = SnackbarDuration.Long
                    )
                    updateEmailAuthenticationState(EmailAuthenticationState.None)
                }
            } else {
                signUpCreateEmailScreenState.updateAuthenticationCodeTextFieldView(true)
                signUpCreateEmailScreenState.updateAuthenticationButtonView(true)
            }
        }

        EmailAuthenticationState.Loading -> {}
    }

    when (
        val result = emailValidationState) {
        is EmailValidationState.Fail -> {
            LaunchedEffect(result.message) {
                signUpCreateEmailScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Long
                )
                updateEmailValidationState(EmailValidationState.None)
            }
        }

        EmailValidationState.None -> {}
        is EmailValidationState.Success -> {
            if (!result.isSuccess) {
                LaunchedEffect(true) {
                    signUpCreateEmailScreenState.snackbarHostState.showSnackbar(
                        message = "인증에 실패하였습니다.", duration = SnackbarDuration.Long
                    )
                    updateEmailValidationState(EmailValidationState.None)
                }
            } else {
                updateCurrentLoginMethodState(
                    LoginMethodState.Default(
                        email = email,
                        password = ""
                    )
                )
                navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph()
            }
        }
    }

    BackHandler(onBack = navigateSignUpToSignInInLoginGraph)


    SignUpCreateEmailScreen(
        modifier,
        email,
        emailAuthenticationCode,
        authenticationTimer,
        emailValidator,
        authenticationCodeValidator,
        createEmailAuthenticationCode,
        validateEmailAuthentication,
        navigateSignUpToSignInInLoginGraph,
        createEmailState,
        emailAuthenticationState,
        signUpCreateEmailScreenState
    )


}


