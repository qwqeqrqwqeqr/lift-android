package com.gradation.lift.feature.my_info

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val MY_INFO_ROUTER_NAME = "myinfo"



fun NavController.navigateToMyInfo(navOptions: NavOptions? = null) {
    this.navigate(MY_INFO_ROUTER_NAME, navOptions)
}