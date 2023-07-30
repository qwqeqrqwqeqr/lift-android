package com.gradation.lift.feature.register_detail.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGenderToHeightWeight
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGenderToName

fun registerDetailGenderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        val navigateRegisterDetailGenderToHeightWeight =
            { navController.navigateRegisterDetailGenderToHeightWeight() }

        val navigateRegisterDetailGenderToName =
            { navController.navigateRegisterDetailGenderToName() }

        navGraphBuilder.composable(Router.REGISTER_DETAIL_GENDER_ROUTER_NAME) {
            RegisterDetailGenderRoute(
                navController = navController,

                navigateRegisterDetailGenderToHeightWeight = navigateRegisterDetailGenderToHeightWeight,
                navigateRegisterDetailGenderToName = navigateRegisterDetailGenderToName
            )
        }

}



