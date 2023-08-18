package com.gradation.lift.feature.register_detail.height_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateHeightWeightToGender
import com.gradation.lift.navigation.navigation.navigateHeightWeightToProfilePicture

fun registerDetailHeightWeightScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateHeightWeightToProfilePicture =
        { navController.navigateHeightWeightToProfilePicture() }
    val navigateHeightWeightToGender =
        { navController.navigateHeightWeightToGender() }


    navGraphBuilder.composable(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        RegisterHeightWeightRoute(
            navController = navController,
            navigateHeightWeightToProfilePicture = navigateHeightWeightToProfilePicture,
            navigateHeightWeightToGender = navigateHeightWeightToGender
        )
    }
}



