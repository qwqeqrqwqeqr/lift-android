package com.gradation.lift.feature.registerDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.register_detail.gender.navigation.genderScreen
import com.gradation.lift.feature.register_detail.height_weight.navigation.heightWeightScreen
import com.gradation.lift.feature.register_detail.name.navigation.nameScreen
import com.gradation.lift.feature.register_detail.profile_picture.navigation.profilePictureScreen
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_GRAPH_NAME
import com.gradation.lift.navigation.Route.REGISTER_DETAIL_NAME_ROUTER_NAME

fun NavGraphBuilder.registerDetailGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = REGISTER_DETAIL_GRAPH_NAME,
        startDestination = REGISTER_DETAIL_NAME_ROUTER_NAME,
        popEnterTransition = null,
        popExitTransition = null,
        enterTransition = null,
        exitTransition = null,
    ) {
        nameScreen(modifier, navController)
        genderScreen(modifier, navController)
        heightWeightScreen(modifier, navController)
        profilePictureScreen(modifier, navController)
    }
}