package com.gradation.lift.feature.login.signUpDefault.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun signUpDefaultScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    navGraphBuilder.composable(Router.LOGIN_SIGN_UP_DEFAULT_ROUTER_NAME) {
        SignUpDefaultRoute()
    }

}


