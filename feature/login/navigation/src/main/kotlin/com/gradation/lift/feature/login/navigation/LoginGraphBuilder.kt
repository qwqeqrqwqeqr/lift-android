package com.gradation.lift.feature.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.login.complete.loginCompleteScreen
import com.gradation.lift.feature.login.find_email_password.loginFindEmailPasswordScreen
import com.gradation.lift.feature.login.signIn.navigation.signInScreen
import com.gradation.lift.feature.login.sign_up.loginSignUpScreen
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Router.LOGIN_SIGN_IN_ROUTER_NAME


fun loginGraphBuilder(
    modifier: Modifier= Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = LOGIN_GRAPH_NAME,
        startDestination = LOGIN_SIGN_IN_ROUTER_NAME,
    ) {
        signInScreen(modifier,navController, this)
        loginSignUpScreen(navController, this)
        loginCompleteScreen(navController, this)
        loginFindEmailPasswordScreen(navController, this)
    }

}