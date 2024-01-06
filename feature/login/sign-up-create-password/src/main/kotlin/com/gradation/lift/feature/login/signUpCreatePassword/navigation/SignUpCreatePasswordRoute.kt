package com.gradation.lift.feature.login.signUpCreatePassword.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.common.data.LoginSharedViewModel
import com.gradation.lift.feature.login.signUpCreatePassword.data.CreatePasswordState
import com.gradation.lift.feature.login.signUpCreatePassword.data.SignUpCreatePasswordScreenState
import com.gradation.lift.feature.login.signUpCreatePassword.data.SignUpCreatePasswordViewModel
import com.gradation.lift.feature.login.signUpCreatePassword.data.rememberSignUpCreatePasswordScreenState
import com.gradation.lift.feature.login.signUpCreatePassword.ui.SignUpCreatePasswordScreen
import com.gradation.lift.feature.login.signUpCreatePassword.ui.dialog.CancelDialog
import com.gradation.lift.navigation.Route

@Composable
fun SignUpCreatePasswordRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateSignUpCreatePasswordToTermsOfUseInLoginGraph: () -> Unit,
    navigateSignUpToSignInInLoginGraph: () -> Unit,
    viewModel: SignUpCreatePasswordViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.LOGIN_GRAPH_NAME) }
    ),
    createPasswordState: CreatePasswordState = viewModel.createPasswordState,
    signUpCreatePasswordScreenState: SignUpCreatePasswordScreenState = rememberSignUpCreatePasswordScreenState(),
) {


    val password: String by createPasswordState.password.collectAsStateWithLifecycle()
    val passwordVerification: String by createPasswordState.passwordVerification.collectAsStateWithLifecycle()
    val passwordValidator: Validator by createPasswordState.passwordValidator.collectAsStateWithLifecycle()
    val passwordVerificationValidator: Validator by createPasswordState.passwordVerificationValidator.collectAsStateWithLifecycle()

    val isValidPassword: Boolean by createPasswordState.isValidPassword.collectAsStateWithLifecycle()
    val isValidPasswordVerification : Boolean by createPasswordState.isValidPasswordVerification.collectAsStateWithLifecycle()
    val currentLoginMethodState: LoginMethodState by sharedViewModel.currentLoginMethodState.collectAsStateWithLifecycle()
    val updateCurrentLoginMethodState: () -> Unit = {
        if (currentLoginMethodState is LoginMethodState.Default) {
            sharedViewModel.updateCurrentLoginMethodState(
                LoginMethodState.Default(
                    email = (currentLoginMethodState as LoginMethodState.Default).email,
                    password = password
                )
            )
        }
    }



    BackHandler(onBack = { signUpCreatePasswordScreenState.updateCancelDialogView(true) })


    AnimatedVisibility(visible = signUpCreatePasswordScreenState.cancelDialogView) {
        CancelDialog(
            modifier = modifier,
            onClickDialogCancelButton = navigateSignUpToSignInInLoginGraph,
            onClickDialogDismissButton = { signUpCreatePasswordScreenState.updateCancelDialogView(false) }
        )
    }


    SignUpCreatePasswordScreen(
        modifier,
        password,
        passwordVerification,
        passwordValidator,
        passwordVerificationValidator,
        isValidPassword,
        isValidPasswordVerification,
        createPasswordState,
        signUpCreatePasswordScreenState,
        updateCurrentLoginMethodState,
        navigateSignUpCreatePasswordToTermsOfUseInLoginGraph,
    )

}
