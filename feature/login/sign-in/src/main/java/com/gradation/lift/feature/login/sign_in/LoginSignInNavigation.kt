package com.gradation.lift.feature.login.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.*

fun loginSignInScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

        navGraphBuilder.composable(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
            LoginSignInRoute(
                navigateToLoginFindEmail = { navController.navigateToLoginFindEmail() },
                navigateToLoginFindPassword =  { navController.navigateToLoginFindPassword() },
                navigateToLoginSignUp = { navController.navigateToLoginSignUp() },
                navigateLoginToHome= { navController.navigateLoginToHome() },
                navigateLoginToRegisterDetail= { navController.navigateLoginToRegisterDetail() }
            )

    }

}
