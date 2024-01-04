package com.gradation.lift.feature.register_detail.profile_picture.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGraphToHomeGraph
import com.gradation.lift.navigation.navigation.navigateResisterDetailGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateToGenderInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToHeightWeightInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToNameInRegisterDetailGraph


fun NavGraphBuilder.profilePictureScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateResisterDetailGraphToLoginGraph: () -> Unit =
        { navController.navigateResisterDetailGraphToLoginGraph() }
    val navigateRegisterDetailGraphToHomeGraph: () -> Unit =
        { navController.navigateRegisterDetailGraphToHomeGraph() }

    val navigateToNameInRegisterDetailGraph: () -> Unit =
        { navController.navigateToNameInRegisterDetailGraph() }
    val navigateToGenderInRegisterDetailGraph: () -> Unit =
        { navController.navigateToGenderInRegisterDetailGraph() }
    val navigateToHeightWeightInRegisterDetailGraph: () -> Unit =
        { navController.navigateToHeightWeightInRegisterDetailGraph() }

    composable(Router.REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME) {
        RegisterDetailProfilePictureRoute(
            modifier,
            navController,
            navigateResisterDetailGraphToLoginGraph,
            navigateRegisterDetailGraphToHomeGraph,
            navigateToNameInRegisterDetailGraph,
            navigateToGenderInRegisterDetailGraph,
            navigateToHeightWeightInRegisterDetailGraph
        )
    }

}



