package com.gradation.lift.inquiry.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.inquiry.registerInquiry.navigation.registerInquiryScreen
import com.gradation.lift.navigation.Route.INQUIRY_GRAPH_NAME
import com.gradation.lift.navigation.Route.INQUIRY_REGISTER_INQUIRY_ROUTER_NAME


fun NavGraphBuilder.inquiryGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = INQUIRY_GRAPH_NAME,
        startDestination = INQUIRY_REGISTER_INQUIRY_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        registerInquiryScreen(modifier, navController)
    }
}
