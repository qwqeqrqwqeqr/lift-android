package com.gradation.lift.navigation.route.create_routine

import androidx.navigation.NavGraphBuilder

fun interface CreateRoutineGraph {
    fun createRoutineGraph(
        route: String,
        startDestination: String,
        navGraphBuilder: NavGraphBuilder,
        nestedGraphs: NavGraphBuilder.() -> Unit
    )
}