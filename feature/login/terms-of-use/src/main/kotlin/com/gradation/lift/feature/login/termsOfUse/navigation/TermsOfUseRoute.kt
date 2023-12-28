package com.gradation.lift.feature.login.termsOfUse.navigation

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
import com.gradation.lift.feature.login.common.data.LoginMethodState
import com.gradation.lift.feature.login.common.data.LoginSharedViewModel
import com.gradation.lift.feature.login.common.data.TermsOfUseState
import com.gradation.lift.feature.login.termsOfUse.data.TermsOfUseViewModel
import com.gradation.lift.feature.login.termsOfUse.data.state.SignUpState
import com.gradation.lift.feature.login.termsOfUse.data.state.TermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.data.state.rememberTermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.ui.TermsOfUseScreen
import com.gradation.lift.navigation.Router

@Composable
fun TermsOfUseRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateTermsOfUseToTermsOfUseDetailInLoginGraph: () -> Unit,
    navigateTermsOfUseToCompleteInLoginGraph: () -> Unit,
    navigateTermsOfUseToSignInInLoginGraph: () -> Unit,
    viewModel: TermsOfUseViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Router.LOGIN_GRAPH_NAME) }
    ),
    termsOfUseScreenState: TermsOfUseScreenState = rememberTermsOfUseScreenState(),
) {

    val currentLoginMethodState: LoginMethodState by sharedViewModel.currentLoginMethodState.collectAsStateWithLifecycle()
    val signUpState: SignUpState by viewModel.signUpState.collectAsStateWithLifecycle()


    val updateSignUpState: (SignUpState) -> Unit = viewModel.updateSignUpState
    val updateTermsOfUseState: (TermsOfUseState) -> Unit = sharedViewModel.updateTermsOfUseState

    val createUserTermsConsent: (Boolean, Boolean) -> Unit =
        { consent, marketingConsent ->
            viewModel.createUserTermsConsent(
                consent, marketingConsent, currentLoginMethodState
            )
        }


    when (val signUpStateResult = signUpState) {
        is SignUpState.Fail -> {
            LaunchedEffect(true) {
                termsOfUseScreenState.snackbarHostState.showSnackbar(
                    message = signUpStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignUpState(SignUpState.None)
            }
        }

        SignUpState.None -> {}
        SignUpState.Success -> {
            updateSignUpState(SignUpState.None)
            navigateTermsOfUseToCompleteInLoginGraph()
        }
    }

    BackHandler(onBack = navigateTermsOfUseToSignInInLoginGraph)

    TermsOfUseScreen(
        modifier,
        termsOfUseScreenState,
        createUserTermsConsent,
        updateTermsOfUseState,
        navigateTermsOfUseToSignInInLoginGraph,
        navigateTermsOfUseToTermsOfUseDetailInLoginGraph
    )
}
