package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.MY_INFO_CANCEL_MEMBERSHIP_CONFIRM_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToLoginGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY

fun NavGraphBuilder.cancelMembershipConfirmScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph: () -> Unit =
        {
            navController.navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph()
        }
    val navigateMyInfoGraphToLoginGraph: () -> Unit = {
        navController.navigateMyInfoGraphToLoginGraph()
    }

    composable(
        route = "$MY_INFO_CANCEL_MEMBERSHIP_CONFIRM_ROUTER_NAME/{$MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY}",
        arguments = listOf(
            navArgument(MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY) { type = NavType.StringType }
        )
    ) {


        CancelMembershipConfirmRoute(
            modifier,
            navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph,
            navigateMyInfoGraphToLoginGraph
        )
    }

}


