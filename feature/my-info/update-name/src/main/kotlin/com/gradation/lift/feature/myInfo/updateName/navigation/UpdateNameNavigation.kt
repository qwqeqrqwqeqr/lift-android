package com.gradation.lift.feature.myInfo.updateName.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.navigation.navigateUpdateNameToProfileInMyInfoGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey

fun NavGraphBuilder.updateNameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(
        route = "${Route.MY_INFO_UPDATE_NAME_ROUTER_NAME}/{${SavedStateHandleKey.MyInfo.USER_NAME_KEY}}",
        arguments = listOf(navArgument(SavedStateHandleKey.MyInfo.USER_NAME_KEY) { type = NavType.StringType }),
    ) {
        val navigateUpdateNameToProfileInMyInfoGraph: () -> Unit = { navController.navigateUpdateNameToProfileInMyInfoGraph() }

        UpdateNameRoute(
            modifier,
            navigateUpdateNameToProfileInMyInfoGraph,
        )
    }


}



