package com.gradation.lift.navigation.route.main

import androidx.navigation.NavGraphBuilder

fun interface RoutineRoute {

    fun routineScreen(route: String, navGraphBuilder: NavGraphBuilder)
}