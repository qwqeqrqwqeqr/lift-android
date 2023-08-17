package com.gradation.lift.feature.register_detail.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGraphToHomeGraph


fun registerDetailProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    val navigateRegisterDetailProfilePictureToUnitOfWeight =
        {  }

    val navigateRegisterDetailGraphToHomeGraph = { navController.navigateRegisterDetailGraphToHomeGraph() }

    navGraphBuilder.composable(Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
        RegisterDetailProfilePictureRoute(
            navController = navController,
            navigateRegisterDetailProfilePictureToUnitOfWeight = navigateRegisterDetailProfilePictureToUnitOfWeight,
            navigateRegisterDetailGraphToHomeGraph = navigateRegisterDetailGraphToHomeGraph
        )
    }

}



