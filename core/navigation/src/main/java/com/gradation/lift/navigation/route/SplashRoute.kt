package com.gradation.lift.navigation.route

import androidx.navigation.NavGraphBuilder



fun interface SplashRoute {

    fun splashScreen(route: String, navGraphBuilder: NavGraphBuilder)
}