package com.gradation.lift.feature.register_detail.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailProfilePictureToUnitOfWeight
import com.gradation.lift.navigation.navigation.navigateRegisterDetailToHome

import com.gradation.lift.navigation.route.register_detail.RegisterDetailProfilePictureRoute

fun registerDetailProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailProfilePictureRoute { route ->

        val navigateRegisterDetailProfilePictureToUnitOfWeight =
            { navController.navigateRegisterDetailProfilePictureToUnitOfWeight() }

        val navigateRegisterDetailToHome = { navController.navigateRegisterDetailToHome() }

        navGraphBuilder.composable(route) {
            RegisterDetailProfilePictureRoute(
                navController = navController,
                navigateRegisterDetailProfilePictureToUnitOfWeight=navigateRegisterDetailProfilePictureToUnitOfWeight,
                navigateRegisterDetailToHome=navigateRegisterDetailToHome
            )
        }
    }.registerDetailProfilePictureScreen(route = Router.REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME)
}



