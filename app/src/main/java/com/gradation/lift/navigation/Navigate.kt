package com.gradation.lift.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate("home", navOptions)
}

fun NavController.navigateToRoutine(navOptions: NavOptions? = null) {
    this.navigate("routine", navOptions)
}

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate("history", navOptions)
}

fun NavController.navigateToMyInfo(navOptions: NavOptions? = null) {
    this.navigate("my-info", navOptions)
}