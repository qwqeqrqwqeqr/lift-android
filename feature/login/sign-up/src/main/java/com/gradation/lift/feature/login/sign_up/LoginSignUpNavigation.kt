package com.gradation.lift.feature.login.sign_up

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateSignUpToComplete
import com.gradation.lift.navigation.navigation.navigateSignUpToSignIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun loginSignUpScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateSignUpToComplete: () -> Unit = { navController.navigateSignUpToComplete() }
    val navigateSignUpToSignIn: () -> Unit = { navController.navigateSignUpToSignIn() }

    navGraphBuilder.composable(Router.LOGIN_SIGN_UP_ROUTER_NAME) {
        LoginSignUpRoute(
            navigateSignUpToComplete = navigateSignUpToComplete,
            navigateSignUpToSignIn = navigateSignUpToSignIn
        )

    }
}



