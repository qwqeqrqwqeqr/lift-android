package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.register_detail.gender.registerDetailGenderScreen
import com.gradation.lift.feature.register_detail.height_weight.registerDetailHeightWeightScreen
import com.gradation.lift.feature.register_detail.name.registerDetailNameScreen
import com.gradation.lift.feature.register_detail.profile_picture.registerDetailProfilePictureScreen
import com.gradation.lift.feature.register_detail.unit_of_weight.registerDetailUnitOfWeightScreen
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.REGISTER_DETAIL_NAME_ROUTER_NAME

fun registerDetailGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = REGISTER_DETAIL_GRAPH_ROUTER_NAME,
        startDestination = REGISTER_DETAIL_NAME_ROUTER_NAME,
    ) {
        registerDetailGenderScreen(navController, this)
        registerDetailHeightWeightScreen(navController, this)
        registerDetailNameScreen(navController, this)
        registerDetailUnitOfWeightScreen(navController, this)
        registerDetailProfilePictureScreen(navController, this)
    }
}