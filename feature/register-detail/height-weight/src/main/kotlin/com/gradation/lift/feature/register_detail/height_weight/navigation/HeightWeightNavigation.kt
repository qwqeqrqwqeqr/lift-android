package com.gradation.lift.feature.register_detail.height_weight.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateResisterDetailGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateToGenderInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToNameInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToProfilePictureInRegisterDetailGraph

fun NavGraphBuilder.heightWeightScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateResisterDetailGraphToLoginGraph: () -> Unit =
        { navController.navigateResisterDetailGraphToLoginGraph() }
    val navigateToNameInRegisterDetailGraph: () -> Unit =
        { navController.navigateToNameInRegisterDetailGraph() }
    val navigateToGenderInRegisterDetailGraph: () -> Unit =
        { navController.navigateToGenderInRegisterDetailGraph() }
    val navigateToProfilePictureInRegisterDetailGraph: () -> Unit =
        { navController.navigateToProfilePictureInRegisterDetailGraph() }

    composable(Router.REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME) {
        HeightWeightRoute(
            modifier,
            navController,
            navigateResisterDetailGraphToLoginGraph,
            navigateToNameInRegisterDetailGraph,
            navigateToGenderInRegisterDetailGraph,
            navigateToProfilePictureInRegisterDetailGraph
        )
    }
}



