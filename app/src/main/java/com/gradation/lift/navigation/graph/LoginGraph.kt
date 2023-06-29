package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.find_workpart.createRoutineFindWorkpartScreen
import com.gradation.lift.feature.login.find_email.loginFindEmailScreen
import com.gradation.lift.feature.login.find_password.loginFindPasswordScreen
import com.gradation.lift.feature.login.sign_in.loginSignInScreen
import com.gradation.lift.feature.login.sign_up.loginSignUpScreen
import com.gradation.lift.feature.login.terms_of_use.loginTermsOfUseScreen
import com.gradation.lift.feature.login.verification.loginVerificationScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.LOGIN_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_SIGN_IN_ROUTER_NAME
import com.gradation.lift.navigation.route.login.LoginGraph

fun loginGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    LoginGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            loginSignInScreen(navController, this)
            loginSignUpScreen(navController, this)
            loginVerificationScreen(navController, this)
            loginTermsOfUseScreen(navController, this)
            loginFindEmailScreen(navController, this)
            loginFindPasswordScreen(navController, this)
        }
    }.loginGraph(
        route = LOGIN_GRAPH_ROUTER_NAME,
        startDestination = LOGIN_SIGN_IN_ROUTER_NAME,
    )
}