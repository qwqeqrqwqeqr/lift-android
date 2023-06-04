package com.gradation.lift.navigation.routor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gradation.lift.feature.home.HomeRoute

const val HOME_ROUTER_NAME = "home"


fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTER_NAME, navOptions)
}

fun NavGraphBuilder.homeScreen() {
  composable(route = HOME_ROUTER_NAME){
      HomeRoute()
  }
}