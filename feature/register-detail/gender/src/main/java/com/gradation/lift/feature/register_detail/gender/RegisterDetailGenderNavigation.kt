package com.gradation.lift.feature.register_detail.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateGenderToHeightWeightInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateGenderToNameInRegisterDetailGraph

fun registerDetailGenderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        val navigateGenderToHeightWeightInRegisterDetailGraph: ()->Unit =
            { navController.navigateGenderToHeightWeightInRegisterDetailGraph() }

        val navigateGenderToNameInRegisterDetailGraph: ()->Unit =
            { navController.navigateGenderToNameInRegisterDetailGraph() }

        navGraphBuilder.composable(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            RegisterDetailGenderRoute(
                navController = navController,
                navigateGenderToHeightWeightInRegisterDetailGraph = navigateGenderToHeightWeightInRegisterDetailGraph,
                navigateGenderToNameInRegisterDetailGraph = navigateGenderToNameInRegisterDetailGraph
            )
        }

}



