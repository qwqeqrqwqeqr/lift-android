package com.gradation.lift.feature.register_detail.height_weight

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailHeightWeightToGender
import com.gradation.lift.navigation.navigation.navigateRegisterDetailHeightWeightToUnitOfWeight
import com.gradation.lift.navigation.route.register_detail.RegisterDetailHeightWeightRoute

fun registerDetailHeightWeightScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRegisterDetailHeightWeightToUnitOfWeight =
        { navController.navigateRegisterDetailHeightWeightToUnitOfWeight() }
    val navigateRegisterDetailHeightWeightToGender =
        { navController.navigateRegisterDetailHeightWeightToGender() }


    RegisterDetailHeightWeightRoute { route ->
        navGraphBuilder.composable(route) {
            RegisterHeightWeightRoute(
                navController = navController,
                navigateRegisterDetailHeightWeightToUnitOfWeight = navigateRegisterDetailHeightWeightToUnitOfWeight,
                navigateRegisterDetailHeightWeightToGender = navigateRegisterDetailHeightWeightToGender
            )
        }
    }.registerDetailHeightWeightScreen(route = Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME)
}



