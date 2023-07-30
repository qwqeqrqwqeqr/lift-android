package com.gradation.lift.navigation.navigation

import androidx.navigation.*
import com.gradation.lift.navigation.Router

fun NavController.navigateToLoginSignIn() {
    this.navigate(Router.LOGIN_SIGN_IN_ROUTER_NAME)
}

fun NavController.navigateToLoginSignUp() {
    this.navigate(Router.LOGIN_SIGN_UP_ROUTER_NAME)
}

fun NavController.navigateToLoginTermsOfUse() {
    this.navigate(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}

fun NavController.navigateToLoginVerification() {
    this.navigate(Router.LOGIN_VERIFICATION_ROUTER_NAME)
}

fun NavController.navigateToLoginComplete() {
    this.navigate(Router.LOGIN_COMPLETE_ROUTER_NAME)
}

fun NavController.navigateToLoginFindEmail() {
    this.navigate(Router.LOGIN_FIND_EMAIL_ROUTER_NAME)
}

fun NavController.navigateToLoginFindPassword() {
    this.navigate(Router.LOGIN_FIND_PASSWORD_ROUTER_NAME)
}

fun NavController.navigateToLoginGraph() {
    this.navigate(Router.LOGIN_GRAPH_NAME) {

    }
}





fun NavController.navigateSignUpProcessToSignIn(){
    this.navigate(Router.LOGIN_SIGN_IN_ROUTER_NAME){
        popUpTo(Router.LOGIN_SIGN_UP_ROUTER_NAME){
            inclusive = true
        }
    }
}


