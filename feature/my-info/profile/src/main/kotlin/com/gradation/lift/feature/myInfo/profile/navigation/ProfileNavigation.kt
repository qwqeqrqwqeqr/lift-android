package com.gradation.lift.feature.myInfo.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateResisterDetailGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateToHeightWeightInRegisterDetailGraph
import com.gradation.lift.navigation.navigation.navigateToNameInRegisterDetailGraph

fun NavGraphBuilder.profileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    composable(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        ProfileRoute(
            modifier,
        )
    }

}



