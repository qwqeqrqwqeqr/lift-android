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

fun registerDetailGraphBuilder(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = REGISTER_DETAIL_GRAPH_NAME,
        startDestination = REGISTER_DETAIL_NAME_ROUTER_NAME,
    ) {
        nameScreen(modifier,navController)
        genderScreen(modifier,navController)
        heightWeightScreen(modifier,navController)
        profilePictureScreen(modifier,navController)
    }
}