package com.gradation.lift.navigation.route.main


fun interface MainGraph {
    fun mainGraph(
        route: String,
        startDestination: String,
    )
}