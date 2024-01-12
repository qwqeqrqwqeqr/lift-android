package com.gradation.lift.feature.myInfo.myInfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateProfileToUpdateProfilePictureInMyInfoGraph

fun myInfoMyInfoScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    val navigateMyInfoGraphToLoginGraph: () -> Unit =
        { navController.navigateMyInfoGraphToLoginGraph() }
    val navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit =
        { navController.navigateProfileToUpdateProfilePictureInMyInfoGraph() }
    val navigateMyInfoToUpdateInMyInfoGraph: () -> Unit =
        { }

    val versionName = navController.context.packageManager.getPackageInfo(
        navController.context.packageName,
        0
    ).versionName

    navGraphBuilder.composable(Route.MY_INFO_MY_INFO_ROUTER_NAME) {

    }
}
