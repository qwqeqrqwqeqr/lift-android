package com.gradation.lift.navigation.route.main

import androidx.navigation.NavGraphBuilder

fun interface HistoryRoute {
    fun historyScreen(route: String, navGraphBuilder: NavGraphBuilder)
}