package com.gradation.lift.feature.my_info.my_info

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToBadgeGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToNotificationGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToUpdateInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToUpdateProfileInMyInfoGraph

fun myInfoMyInfoScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    versionName: String,
) {

    val navigateMyInfoGraphToNotificationGraph:() -> Unit =
        { navController.navigateMyInfoGraphToNotificationGraph() }
    val navigateMyInfoGraphToBadgeGraph:() -> Unit = { navController.navigateMyInfoGraphToBadgeGraph() }

    val navigateMyInfoGraphToLoginGraph:() -> Unit = { navController.navigateMyInfoGraphToLoginGraph() }
    val navigateMyInfoToUpdateProfileInMyInfoGraph:() -> Unit = { navController.navigateMyInfoToUpdateProfileInMyInfoGraph() }
    val navigateMyInfoToUpdateInMyInfoGraph:() -> Unit = { navController.navigateMyInfoToUpdateInMyInfoGraph() }


    navGraphBuilder.composable(Router.MY_INFO_MY_INFO_ROUTER_NAME) {
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
