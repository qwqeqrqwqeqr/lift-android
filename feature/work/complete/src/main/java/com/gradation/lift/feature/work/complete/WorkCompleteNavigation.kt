package com.gradation.lift.feature.work.complete

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_COMPLETE_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph


@RequiresApi(Build.VERSION_CODES.O)
fun workCompleteScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {

    val navigateWorkGraphToHomeGraph = { navController.navigateWorkGraphToHomeGraph() }

    navGraphBuilder.composable(WORK_COMPLETE_ROUTER_NAME) {
        WorkCompleteRoute(
            navController = navController,
            navigateWorkGraphToHomeGraph=navigateWorkGraphToHomeGraph
        )
    }

}
