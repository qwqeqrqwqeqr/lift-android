package com.gradation.lift.feature.register_detail.height_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateHeightWeightToGender

fun registerDetailHeightWeightScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRegisterDetailHeightWeightToUnitOfWeight =
        {  }
    val navigateHeightWeightToGender =
        { navController.navigateHeightWeightToGender() }


    navGraphBuilder.composable(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        RegisterHeightWeightRoute(
            navController = navController,
            navigateRegisterDetailHeightWeightToUnitOfWeight = navigateRegisterDetailHeightWeightToUnitOfWeight,
            navigateHeightWeightToGender = navigateHeightWeightToGender
        )
    }
}



