package com.gradation.lift.feature.register_detail.gender.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateResisterDetailGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateToHeightWeightInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToNameInRegisterDetailGraph

fun NavGraphBuilder.genderScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigateToNameInRegisterDetailGraph: () -> Unit =
        { navController.navigateToNameInRegisterDetailGraph() }
    val navigateToHeightWeightInRegisterDetailGraph: () -> Unit =
        { navController.navigateToHeightWeightInRegisterDetailGraph() }
    val navigateResisterDetailGraphToLoginGraph: () -> Unit =
        { navController.navigateResisterDetailGraphToLoginGraph() }


    composable(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME) {
        GenderRoute(
            modifier,
            navController,
            navigateToNameInRegisterDetailGraph,
            navigateToHeightWeightInRegisterDetailGraph,
            navigateResisterDetailGraphToLoginGraph
        )
    }

}



