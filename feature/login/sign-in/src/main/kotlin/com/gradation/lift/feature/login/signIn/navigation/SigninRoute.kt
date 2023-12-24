package com.gradation.lift.feature.login.signIn.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.login.signIn.SignInScreen
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.feature.login.signIn.data.state.SignInState
import com.gradation.lift.feature.login.signIn.data.state.SignInScreenState
import com.gradation.lift.feature.login.signIn.data.state.rememberSignInScreenState
import com.gradation.lift.feature.login.signIn.data.viewmodel.SignInViewModel
import com.gradation.lift.oauth.common.OAuthConnectState

@Composable
internal fun SignInRoute(
    modifier: Modifier = Modifier,
    navigateLoginGraphToHomeGraph: () -> Unit,
    navigateLoginGraphToRegisterDetailGraph: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
    signInScreenState: SignInScreenState = rememberSignInScreenState(),
) {

    val naverOAuthConnectState: OAuthConnectState by viewModel.oAuthSignInState.naverOAuthConnectState.collectAsStateWithLifecycle()
    val kakaoOAuthConnectState: OAuthConnectState by viewModel.oAuthSignInState.kakaoOAuthConnectState.collectAsStateWithLifecycle()
    val signInState: SignInState by viewModel.oAuthSignInState.oAuthSignInState.collectAsStateWithLifecycle()
    val oAuthSignInState: OAuthSignInState = viewModel.oAuthSignInState

    /**
     *  각 상태에 따른 처리 로직
     *  @since 2023-08-16 22:39:06
     */
    when (val naverConnectStateResult = naverOAuthConnectState) {
        is OAuthConnectState.Fail -> {
            LaunchedEffect(true) {
                signInScreenState.snackbarHostState.showSnackbar(
                    message = naverConnectStateResult.message, duration = SnackbarDuration.Short
                )
                viewModel.oAuthSignInState.updateOAuthSignInState(SignInState.None)
            }
        }

        OAuthConnectState.None -> {}
        OAuthConnectState.Success -> {
            LaunchedEffect(true) {
                viewModel.oAuthSignInState.signInNaver()
            }
        }
    }
    when (val kakaoConnectStateResult = kakaoOAuthConnectState) {
        is OAuthConnectState.Fail -> {
            LaunchedEffect(true) {
                signInScreenState.snackbarHostState.showSnackbar(
                    message = kakaoConnectStateResult.message, duration = SnackbarDuration.Short
                )
                viewModel.oAuthSignInState.updateOAuthSignInState(SignInState.None)
            }
        }

        OAuthConnectState.None -> {}
        OAuthConnectState.Success -> {
            LaunchedEffect(true) {
                viewModel.oAuthSignInState.signInKakao()
            }
        }
    }
    when (val signInStateResult: SignInState = signInState) {
        is SignInState.Fail -> {
            LaunchedEffect(true) {
                signInScreenState.snackbarHostState.showSnackbar(
                    message = signInStateResult.message, duration = SnackbarDuration.Short
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
        signInScreenState = signInScreenState
    )
}