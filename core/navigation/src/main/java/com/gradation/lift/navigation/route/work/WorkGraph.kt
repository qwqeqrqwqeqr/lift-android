package com.gradation.lift.navigation.route.work


fun interface WorkGraph {
    fun workGraph(
        route: String,
        startDestination: String,
    )
}