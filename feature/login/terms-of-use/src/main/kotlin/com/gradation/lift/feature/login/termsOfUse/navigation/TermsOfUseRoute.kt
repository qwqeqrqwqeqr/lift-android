package com.gradation.lift.feature.login.termsOfUse.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
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
import com.gradation.lift.feature.login.termsOfUse.data.state.CreateUserTermsConsentState
import com.gradation.lift.feature.login.termsOfUse.data.state.SignUpState
import com.gradation.lift.feature.login.termsOfUse.data.state.TermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.data.state.rememberTermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.ui.TermsOfUseScreen
import com.gradation.lift.feature.login.termsOfUse.ui.component.dialog.CompleteDialog
import com.gradation.lift.navigation.Route
import com.gradation.lift.ui.extensions.showImmediatelySnackbar

@Composable
fun TermsOfUseRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateTermsOfUseToTermsOfUseDetailInLoginGraph: () -> Unit,
    navigateLoginGraphToRegisterDetailGraph: () -> Unit,
    navigateTermsOfUseToSignInInLoginGraph: () -> Unit,
    viewModel: TermsOfUseViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.LOGIN_GRAPH_NAME) }
    ),
    termsOfUseScreenState: TermsOfUseScreenState = rememberTermsOfUseScreenState(),
) {

    val currentLoginMethodState: LoginMethodState by sharedViewModel.currentLoginMethodState.collectAsStateWithLifecycle()
    val signUpState: SignUpState by viewModel.signUpState.collectAsStateWithLifecycle()
    val createUserTermsConsentState: CreateUserTermsConsentState by viewModel.createUserTermsConsentState.collectAsStateWithLifecycle()


    val updateSignUpState: (SignUpState) -> Unit = viewModel.updateSignUpState
    val updateCreateUserTermsConsentState: (CreateUserTermsConsentState) -> Unit =
        viewModel.updateCreateUserTermsConsentState
    val updateTermsOfUseState: (TermsOfUseState) -> Unit = sharedViewModel.updateTermsOfUseState

    val signUp: () -> Unit = { viewModel.signUp(currentLoginMethodState) }
    val createUserTermsConsent: () -> Unit =
        { viewModel.createUserTermsConsent(consent = true, marketingConsent = false) }

    when (val signUpStateResult = signUpState) {
        is SignUpState.Fail -> {
            LaunchedEffect(true) {
                termsOfUseScreenState.snackbarHostState.showImmediatelySnackbar(
                    message = signUpStateResult.message,
                )
                updateSignUpState(SignUpState.None)
            }
        }

        SignUpState.None -> {}
        SignUpState.Success -> {
            updateSignUpState(SignUpState.None)
            createUserTermsConsent()
        }
    }


    when (val createUserTermsConsentStateResult = createUserTermsConsentState) {
        is CreateUserTermsConsentState.Fail -> {
            LaunchedEffect(true) {
                termsOfUseScreenState.snackbarHostState.showImmediatelySnackbar(
                    message = createUserTermsConsentStateResult.message,

                    )
                updateCreateUserTermsConsentState(CreateUserTermsConsentState.None)
            }
        }

        CreateUserTermsConsentState.None -> {}
        CreateUserTermsConsentState.Success -> {
            updateCreateUserTermsConsentState(CreateUserTermsConsentState.None)
            termsOfUseScreenState.updateCompleteDialogView(true)
        }
    }






    BackHandler(onBack = navigateTermsOfUseToSignInInLoginGraph)
    AnimatedVisibility(visible = termsOfUseScreenState.completeDialogView.value) {
        CompleteDialog(
            modifier,
            navigateLoginGraphToRegisterDetailGraph
        )
    }
    TermsOfUseScreen(
        modifier,
        termsOfUseScreenState,
        signUp,
        updateTermsOfUseState,
        navigateTermsOfUseToSignInInLoginGraph,
        navigateTermsOfUseToTermsOfUseDetailInLoginGraph
    )
}
