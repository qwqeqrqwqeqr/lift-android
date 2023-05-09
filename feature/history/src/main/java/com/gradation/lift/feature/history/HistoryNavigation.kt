package com.gradation.lift.feature.history

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val HISTORY_ROUTER_NAME = "history"



fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(HISTORY_ROUTER_NAME, navOptions)
}