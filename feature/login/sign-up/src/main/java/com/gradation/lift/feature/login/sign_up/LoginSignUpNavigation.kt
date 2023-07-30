package com.gradation.lift.feature.login.sign_up

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun loginSignUpScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    navGraphBuilder.composable(Router.LOGIN_SIGN_UP_ROUTER_NAME) {
        LoginSignUpRoute(navController)
    }
}



