package com.gradation.lift.navigation.route.ready_work


fun interface ReadyWorkGraph {
    fun readyWorkGraph(
        route: String,
        startDestination: String,
    )
}