package com.gradation.lift.feature.register_detail.name.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateResisterDetailGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateToGenderInRegisterDetailGraph

fun NavGraphBuilder.nameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateToGenderInRegisterDetailGraph: () -> Unit =
        { navController.navigateToGenderInRegisterDetailGraph() }
    val navigateResisterDetailGraphToLoginGraph: () -> Unit =
        { navController.navigateResisterDetailGraphToLoginGraph() }


    composable(Router.REGISTER_DETAIL_NAME_ROUTER_NAME) {
        NameRoute(
            modifier,
            navController,
            navigateToGenderInRegisterDetailGraph,
            navigateResisterDetailGraphToLoginGraph
        )
    }

}



