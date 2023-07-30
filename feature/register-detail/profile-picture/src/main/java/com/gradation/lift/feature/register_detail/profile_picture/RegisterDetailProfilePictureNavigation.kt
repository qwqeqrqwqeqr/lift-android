package com.gradation.lift.feature.register_detail.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailProfilePictureToUnitOfWeight
import com.gradation.lift.navigation.navigation.navigateRegisterDetailToHome


fun registerDetailProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    val navigateRegisterDetailProfilePictureToUnitOfWeight =
        { navController.navigateRegisterDetailProfilePictureToUnitOfWeight() }

    val navigateRegisterDetailToHome = { navController.navigateRegisterDetailToHome() }

    navGraphBuilder.composable(Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
        RegisterDetailProfilePictureRoute(
            navController = navController,
            navigateRegisterDetailProfilePictureToUnitOfWeight = navigateRegisterDetailProfilePictureToUnitOfWeight,
            navigateRegisterDetailToHome = navigateRegisterDetailToHome
        )
    }

}



