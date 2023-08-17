package com.gradation.lift.feature.register_detail.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateGenderToHeightWeight
import com.gradation.lift.navigation.navigation.navigateGenderToName

fun registerDetailGenderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        val navigateGenderToHeightWeight =
            { navController.navigateGenderToHeightWeight() }

        val navigateGenderToName =
            { navController.navigateGenderToName() }

        navGraphBuilder.composable(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            RegisterDetailGenderRoute(
                navController = navController,

                navigateGenderToHeightWeight = navigateGenderToHeightWeight,
                navigateGenderToName = navigateGenderToName
            )
        }

}



