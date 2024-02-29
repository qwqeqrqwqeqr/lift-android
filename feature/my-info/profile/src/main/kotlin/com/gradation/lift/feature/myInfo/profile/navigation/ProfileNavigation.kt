package com.gradation.lift.feature.myInfo.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateMyInfoGraphToLoginGraph
import com.gradation.lift.navigation.navigation.navigateProfileToCancelMembershipInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateProfileToMyInfoInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateProfileToUpdateInfoInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateProfileToUpdateNameInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateProfileToUpdateProfilePictureInMyInfoGraph

fun NavGraphBuilder.profileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateProfileToMyInfoInMyInfoGraph: () -> Unit =
        { navController.navigateProfileToMyInfoInMyInfoGraph() }

    val navigateProfileToUpdateNameInMyInfoGraph: (String) -> Unit =
        { navController.navigateProfileToUpdateNameInMyInfoGraph(it) }

    val navigateProfileToUpdateInfoInMyInfoGraph: (String, Float, Float) -> Unit =
        { gender, height, weight ->
            navController.navigateProfileToUpdateInfoInMyInfoGraph(
                gender,
                height,
                weight
            )
        }

    val navigateProfileToUpdateProfilePictureInMyInfoGraph: (String) -> Unit =
        { navController.navigateProfileToUpdateProfilePictureInMyInfoGraph(it) }

    val navigateMyInfoGraphToLoginGraph: () -> Unit =
        { navController.navigateMyInfoGraphToLoginGraph() }


    val navigateProfileToCancelMembershipInMyInfoGraph: () -> Unit =
        { navController.navigateProfileToCancelMembershipInMyInfoGraph() }

    composable(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        ProfileRoute(
            modifier,
            navigateProfileToMyInfoInMyInfoGraph,
            navigateProfileToUpdateNameInMyInfoGraph,
            navigateProfileToUpdateInfoInMyInfoGraph,
            navigateProfileToUpdateProfilePictureInMyInfoGraph,
            navigateProfileToCancelMembershipInMyInfoGraph,
            navigateMyInfoGraphToLoginGraph
        )
    }

}



