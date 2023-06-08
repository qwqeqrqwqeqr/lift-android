package com.gradation.lift.navigation.route

import androidx.navigation.NavGraphBuilder

fun interface HomeRoute {
    fun homeScreen(router: String, navGraphBuilder: NavGraphBuilder)
}


