package com.gradation.lift.feature.login.signIn.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.common.data.LoginSharedViewModel
import com.gradation.lift.feature.login.signIn.data.state.ConnectState
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.feature.login.signIn.data.state.SignInScreenState
import com.gradation.lift.feature.login.signIn.data.state.SignInState
import com.gradation.lift.feature.login.signIn.data.state.rememberSignInScreenState
import com.gradation.lift.feature.login.signIn.data.viewmodel.SignInViewModel
import com.gradation.lift.feature.login.signIn.ui.SignInScreen
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.navigation.Route
import com.gradation.lift.ui.extensions.showImmediatelySnackbar

@Composable
internal fun SignInRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateLoginGraphToHomeGraph: () -> Unit,
    navigateLoginGraphToRegisterDetailGraph: () -> Unit,
    navigateSignInToSignInDefaultInLoginGraph: () -> Unit,
    navigateSignInToSignUpCreateEmailDefaultInLoginGraph: () -> Unit,
    navigateSignInToTermsOfUseInLoginGraph: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.LOGIN_GRAPH_NAME) }
    ),
    signInScreenState: SignInScreenState = rememberSignInScreenState(),
) {

    val signInState: SignInState by viewModel.oAuthSignInState.oAuthSignInState.collectAsStateWithLifecycle()
    val connectState: ConnectState by viewModel.oAuthSignInState.connectState.collectAsStateWithLifecycle()
    val oAuthSignInState: OAuthSignInState = viewModel.oAuthSignInState


    when (val connectStateResult: ConnectState = connectState) {
        is ConnectState.Fail -> {
            LaunchedEffect(connectStateResult.message) {
                signInScreenState.snackbarHostState.showImmediatelySnackbar(
                    connectStateResult.message,
                )
                viewModel.oAuthSignInState.updateConnectState(ConnectState.None)
            }
        }

        ConnectState.None -> {}

        is ConnectState.Success -> {
            LaunchedEffect(true) {
                if (connectStateResult.isSigned) {
                    when (connectStateResult.loginMethod) {
                        is LoginMethod.Google ->
                            oAuthSignInState.signInGoogle()

                        is LoginMethod.Kakao ->
                            oAuthSignInState.signInKakao()

                        else ->
                            oAuthSignInState.signInNaver()
                    }
                } else {
                    when (connectStateResult.loginMethod) {
                        is LoginMethod.Google ->
                            sharedViewModel.updateCurrentLoginMethodState(LoginMethodState.Google)

                        is LoginMethod.Kakao ->
                            sharedViewModel.updateCurrentLoginMethodState(LoginMethodState.Kakao)

                        else ->
                            sharedViewModel.updateCurrentLoginMethodState(LoginMethodState.Naver)
                    }
                    navigateSignInToTermsOfUseInLoginGraph()
                }
                viewModel.oAuthSignInState.updateConnectState(ConnectState.None)
            }
        }
    }



    when (val signInStateResult: SignInState = signInState) {
        is SignInState.Fail -> {
            LaunchedEffect(true) {
                signInScreenState.snackbarHostState.showImmediatelySnackbar(
                    signInStateResult.message,
                )
                viewModel.oAuthSignInState.updateOAuthSignInState(SignInState.None)
            }
        }

        SignInState.None -> {}
        is SignInState.Success -> {
            LaunchedEffect(signInStateResult) {
                if (signInStateResult.existUserDetail) {
                    navigateLoginGraphToHomeGraph()
                } else {
                    navigateLoginGraphToRegisterDetailGraph()
                }

            }
        }
    }



    SignInScreen(
        modifier = modifier,
        oAuthSignInState = oAuthSignInState,
        signInScreenState = signInScreenState,
        navigateSignInToSignInDefaultInLoginGraph=navigateSignInToSignInDefaultInLoginGraph,
        navigateSignInToSignUpCreateEmailDefaultInLoginGraph=navigateSignInToSignUpCreateEmailDefaultInLoginGraph
    )
}