package com.gradation.lift.navigation.navigation

import androidx.navigation.*
import com.gradation.lift.navigation.Router.LOGIN_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_SIGN_IN_ROUTER_NAME
import com.gradation.lift.navigation.Router.LOGIN_SIGN_UP_ROUTER_NAME
import com.gradation.lift.navigation.Router.MAIN_GRAPH_NAME

fun NavController.navigateLoginGraphToMainGraph() {
    this.navigate(MAIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateLoginGraphToMainGraph.graph.id) {
            inclusive = true
        }
    }
}



fun NavController.navigateSignInToSignUp() {
    this.navigate(LOGIN_SIGN_UP_ROUTER_NAME)
}

fun NavController.navigateSignUpToComplete() {
    this.navigate(LOGIN_COMPLETE_ROUTER_NAME)
}

fun NavController.navigateSignInToFindEmailPassword() {
    this.navigate(LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME)
}

fun NavController.navigateSignUpToSignIn(){
    this.navigate(LOGIN_SIGN_IN_ROUTER_NAME){
        popUpTo(LOGIN_SIGN_IN_ROUTER_NAME){
            inclusive = true
        }
    }
}

fun NavController.navigateCompleteToSignIn(){
    this.navigate(LOGIN_SIGN_IN_ROUTER_NAME){
        popUpTo(LOGIN_SIGN_IN_ROUTER_NAME){
            inclusive = true
        }
    }
}

