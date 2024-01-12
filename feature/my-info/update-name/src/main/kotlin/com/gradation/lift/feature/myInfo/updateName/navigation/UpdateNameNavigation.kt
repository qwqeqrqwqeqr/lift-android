package com.gradation.lift.feature.myInfo.updateName.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route

fun NavGraphBuilder.updateNameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    composable(Route.MY_INFO_UPDATE_NAME_ROUTER_NAME) {
        UpdateNameRoute(
            modifier,
        )
    }

}



