package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.gradation.lift.navigation.Router

fun NavController.navigateToLoginSignIn(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_SIGN_IN_ROUTER_NAME, navOptions)
}

fun NavController.navigateToLoginSignUp(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_SIGN_UP_ROUTER_NAME, navOptions)
}

fun NavController.navigateToLoginTermsOfUse(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME, navOptions)
}

fun NavController.navigateToVerification(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_VERIFICATION_ROUTER_NAME, navOptions)
}

fun NavController.navigateToFindEmail(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_FIND_EMAIL_ROUTER_NAME, navOptions)
}
fun NavController.navigateToFindPassword(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_FIND_PASSWORD_ROUTER_NAME, navOptions)
}

fun NavController.navigateToLoginGraph(navOptions: NavOptions? = null) {
    this.navigate(Router.LOGIN_GRAPH_ROUTER_NAME, navOptions)
}
