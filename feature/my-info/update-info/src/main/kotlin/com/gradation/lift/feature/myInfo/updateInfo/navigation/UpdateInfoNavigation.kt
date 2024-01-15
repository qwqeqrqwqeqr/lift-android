package com.gradation.lift.feature.myInfo.updateInfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.MY_INFO_UPDATE_INFO_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateUpdateInfoToProfileInMyInfoGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.USER_GENDER_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.USER_HEIGHT_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.USER_WEIGHT_KEY

fun NavGraphBuilder.updateInfoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(
        route = "${MY_INFO_UPDATE_INFO_ROUTER_NAME}/{$USER_GENDER_KEY}/{${USER_HEIGHT_KEY}}/{${USER_WEIGHT_KEY}}",
        arguments = listOf(
            navArgument(USER_GENDER_KEY) { type = NavType.StringType },
            navArgument(USER_HEIGHT_KEY) { type = NavType.FloatType },
            navArgument(USER_WEIGHT_KEY) { type = NavType.FloatType },
        )
    ) {
        val navigateUpdateInfoToProfileInMyInfoGraph: () -> Unit = { navController.navigateUpdateInfoToProfileInMyInfoGraph() }

        UpdateInfoRoute(
            modifier,
            navigateUpdateInfoToProfileInMyInfoGraph
        )
    }




}
