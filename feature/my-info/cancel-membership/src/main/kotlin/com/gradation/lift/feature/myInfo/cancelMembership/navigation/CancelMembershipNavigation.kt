package com.gradation.lift.feature.myInfo.cancelMembership.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateCancelMembershipToProfileInMyInfoGraph

fun NavGraphBuilder.cancelMembershipScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph: (String) -> Unit =
        { reason ->
            navController.navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph(reason)
        }
    val navigateCancelMembershipToProfileInMyInfoGraph: () -> Unit = {
        navController.navigateCancelMembershipToProfileInMyInfoGraph()
    }

    composable(
        route = MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME,
    ) {
        CancelMembershipRoute(
            modifier,
            navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph,
            navigateCancelMembershipToProfileInMyInfoGraph
        )
    }

}


