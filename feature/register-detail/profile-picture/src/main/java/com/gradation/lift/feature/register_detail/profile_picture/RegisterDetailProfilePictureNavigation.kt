package com.gradation.lift.feature.register_detail.profile_picture

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateProfilePictureToHeightWeight
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGraphToHomeGraph


fun registerDetailProfilePictureScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRegisterDetailGraphToHomeGraph: () -> Unit = { navController.navigateRegisterDetailGraphToHomeGraph() }
    val navigateProfilePictureToHeightWeight: () -> Unit = { navController.navigateProfilePictureToHeightWeight() }

    navGraphBuilder.composable(Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
        RegisterDetailProfilePictureRoute(
            navController = navController,
            navigateRegisterDetailGraphToHomeGraph = navigateRegisterDetailGraphToHomeGraph,
            navigateProfilePictureToHeightWeight = navigateProfilePictureToHeightWeight
        )
    }

}



