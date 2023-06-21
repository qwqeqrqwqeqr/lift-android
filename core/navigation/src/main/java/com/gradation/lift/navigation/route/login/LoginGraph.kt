package com.gradation.lift.navigation.route.login


fun interface LoginGraph {
    fun loginGraph(
        route: String,
        startDestination: String,
    )
}