package com.gradation.lift.feature.login.signInDefault.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.login.signInDefault.data.SignInDefaultViewModel
import com.gradation.lift.feature.login.signInDefault.data.SignInScreenState
import com.gradation.lift.feature.login.signInDefault.data.SignInState
import com.gradation.lift.feature.login.signInDefault.data.rememberSignInScreenState
import com.gradation.lift.feature.login.signInDefault.ui.SignInDefaultScreen

@Composable
fun SignInDefaultRoute(
    modifier: Modifier = Modifier,
    navigateSignInDefaultToSignInInLoginGraph: () -> Unit,
    navigateSignInDefaultToVerifyEmailInLoginGraph: () -> Unit,
    navigateLoginGraphToHomeGraph: () -> Unit,
    navigateLoginGraphToRegisterDetailGraph: () -> Unit,
    viewModel: SignInDefaultViewModel = hiltViewModel(),
    signInScreenState: SignInScreenState = rememberSignInScreenState(),
) {

    val signInState: SignInState by viewModel.signInState.collectAsStateWithLifecycle()
    val updateSignInState: (SignInState) -> Unit = viewModel.updateSignInState
    val signIn: (String,String) -> Unit = viewModel.signIn



    when(val signInStateResult : SignInState = signInState){
        is SignInState.Fail -> {
            LaunchedEffect(signInStateResult.message) {
                signInScreenState.snackbarHostState.showSnackbar(
                    message = signInStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignInState(SignInState.None)
            }
        }
        SignInState.None -> {}
        is SignInState.Success -> {
            LaunchedEffect(signInStateResult) {
                updateSignInState(SignInState.None)
                if (signInStateResult.existUserDetail) {
                    navigateLoginGraphToHomeGraph()
                } else {
                    navigateLoginGraphToRegisterDetailGraph()
                }
            }
        }
    }

    BackHandler(onBack = navigateSignInDefaultToSignInInLoginGraph)



    SignInDefaultScreen(
        modifier,
        signIn,
        navigateSignInDefaultToSignInInLoginGraph,
        navigateSignInDefaultToVerifyEmailInLoginGraph,
        signInScreenState
    )

}
