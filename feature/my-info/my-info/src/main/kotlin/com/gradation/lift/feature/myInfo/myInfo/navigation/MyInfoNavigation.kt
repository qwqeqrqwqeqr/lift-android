package com.gradation.lift.feature.myInfo.myInfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToNoticeGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoToProfileInMyInfoGraph

fun NavGraphBuilder.myInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {





    val navigateMyInfoGraphToNoticeGraph: () -> Unit =
        { navController.navigateMyInfoGraphToNoticeGraph() }


    val navigateMyInfoToProfileInMyInfoGraph: () -> Unit =
        { navController.navigateMyInfoToProfileInMyInfoGraph() }



    composable(Route.MY_INFO_MY_INFO_ROUTER_NAME) {
        MyInfoRoute(
            modifier,
            navigateMyInfoGraphToNoticeGraph,
            navigateMyInfoToProfileInMyInfoGraph
        )
    }
}
