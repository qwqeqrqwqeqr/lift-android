package com.gradation.lift.feature.work.work

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkToMain
import com.gradation.lift.navigation.navigation.navigateWorkWorkToComplete


fun workWorkScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_WORK_ROUTER_NAME) {

        val navigateWorkWorkToComplete = { navController.navigateWorkWorkToComplete() }
        val navigateWorkToMain = { navController.navigateWorkToMain() }

        WorkWorkRoute(
            navController = navController,
            navigateWorkWorkToComplete=navigateWorkWorkToComplete,
            navigateWorkToMain=navigateWorkToMain
        )
    }

}