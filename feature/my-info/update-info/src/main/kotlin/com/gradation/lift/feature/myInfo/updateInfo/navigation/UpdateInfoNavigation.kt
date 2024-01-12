package com.gradation.lift.feature.myInfo.updateInfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.updateInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(Route.MY_INFO_UPDATE_INFO_ROUTER_NAME) {

        val navigateUpdateToMyInfoInMyInfoGraph: () -> Unit = { }

        UpdateInfoRoute(
            navigateUpdateToMyInfoInMyInfoGraph = navigateUpdateToMyInfoInMyInfoGraph

        )
    }

}
