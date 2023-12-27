package com.gradation.lift.feature.login.signInDefault.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun signInDefaultScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    navGraphBuilder.composable(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
        SignInDefaultRoute()
    }

}


