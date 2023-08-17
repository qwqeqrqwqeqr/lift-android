package com.gradation.lift.feature.register_detail.unit_of_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateHeightWeightToProfilePicture
import com.gradation.lift.navigation.navigation.navigateRegisterDetailUnitOfWeightToProfilePicture

fun registerDetailUnitOfWeightScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateHeightWeightToProfilePicture =
        { navController.navigateHeightWeightToProfilePicture() }
    val navigateRegisterDetailUnitOfWeightToProfilePicture =
        { navController.navigateRegisterDetailUnitOfWeightToProfilePicture() }
    navGraphBuilder.composable(Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME) {
        RegisterDetailUnitOfWeightRoute(
            navController = navController,
            navigateHeightWeightToProfilePicture = navigateHeightWeightToProfilePicture,
            navigateRegisterDetailUnitOfWeightToProfilePicture = navigateRegisterDetailUnitOfWeightToProfilePicture
        )
    }

}



