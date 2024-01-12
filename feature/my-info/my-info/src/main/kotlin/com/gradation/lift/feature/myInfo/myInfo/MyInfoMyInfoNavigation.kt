package com.gradation.lift.feature.myInfo.myInfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToBadgeGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToNotificationGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToUpdateInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToUpdateProfileInMyInfoGraph

fun myInfoMyInfoScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateMyInfoGraphToNotificationGraph:() -> Unit =
        { navController.navigateMyInfoGraphToNotificationGraph() }
    val navigateMyInfoGraphToBadgeGraph:() -> Unit = { navController.navigateMyInfoGraphToBadgeGraph() }

    val navigateMyInfoGraphToLoginGraph:() -> Unit = { navController.navigateMyInfoGraphToLoginGraph() }
    val navigateMyInfoToUpdateProfileInMyInfoGraph:() -> Unit = { navController.navigateMyInfoToUpdateProfileInMyInfoGraph() }
    val navigateMyInfoToUpdateInMyInfoGraph:() -> Unit = { navController.navigateMyInfoToUpdateInMyInfoGraph() }

    val versionName =navController.context.packageManager.getPackageInfo(navController.context.packageName, 0).versionName

    navGraphBuilder.composable(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
        MyInfoMyInfoRoute(
            versionName=versionName,
            navigateMyInfoGraphToLoginGraph=navigateMyInfoGraphToLoginGraph,
            navigateMyInfoToUpdateProfileInMyInfoGraph=navigateMyInfoToUpdateProfileInMyInfoGraph,
            navigateMyInfoToUpdateInMyInfoGraph=navigateMyInfoToUpdateInMyInfoGraph,
            navigateMyInfoGraphToNotificationGraph=navigateMyInfoGraphToNotificationGraph,
            navigateMyInfoGraphToBadgeGraph=navigateMyInfoGraphToBadgeGraph
            )
    }
}
