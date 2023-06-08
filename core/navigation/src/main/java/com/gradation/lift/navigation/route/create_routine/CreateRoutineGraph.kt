package com.gradation.lift.navigation.route.create_routine


fun interface CreateRoutineGraph {
    fun createRoutineGraph(
        route: String,
        startDestination: String,
    )
}