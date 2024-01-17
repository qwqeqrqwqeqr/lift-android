package com.gradation.lift.feature.home.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.home.badge.navigation.newBadgeScreen
import com.gradation.lift.feature.home.home.navigation.homeScreen
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.HOME_HOME_ROUTER_NAME

fun homeGraphBuilder(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = HOME_GRAPH_NAME,
        startDestination = HOME_HOME_ROUTER_NAME,
        popEnterTransition = {fadeIn(animationSpec = tween(1000))},
        popExitTransition = { fadeOut(animationSpec = tween(1000)) },
        enterTransition = {fadeIn(animationSpec = tween(1000))},
        exitTransition = { fadeOut(animationSpec = tween(1000)) },
    ) {
        newBadgeScreen(modifier ,navController)
        homeScreen(modifier,navController)
    }
}
