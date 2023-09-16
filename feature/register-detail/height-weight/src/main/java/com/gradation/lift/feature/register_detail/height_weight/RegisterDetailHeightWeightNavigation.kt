package com.gradation.lift.feature.register_detail.height_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateHeightWeightToGenderInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateHeightWeightToProfilePictureInRegisterDetailGraph

fun registerDetailHeightWeightScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateHeightWeightToProfilePictureInRegisterDetailGraph =
        { navController.navigateHeightWeightToProfilePictureInRegisterDetailGraph() }
    val navigateHeightWeightToGenderInRegisterDetailGraph =
        { navController.navigateHeightWeightToGenderInRegisterDetailGraph() }


    navGraphBuilder.composable(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        RegisterHeightWeightRoute(
            navController = navController,
            navigateHeightWeightToProfilePictureInRegisterDetailGraph = navigateHeightWeightToProfilePictureInRegisterDetailGraph,
            navigateHeightWeightToGenderInRegisterDetailGraph = navigateHeightWeightToGenderInRegisterDetailGraph
        )
    }
}



