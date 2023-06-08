package com.gradation.lift.navigation.route.main

import androidx.navigation.NavGraphBuilder

fun interface MyInfoRoute {
    fun myInfoScreen(route: String, navGraphBuilder: NavGraphBuilder)
}