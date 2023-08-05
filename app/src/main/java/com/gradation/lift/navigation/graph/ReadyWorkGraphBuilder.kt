package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.work.change_order.readyWorkChangeOrderScreen
import com.gradation.lift.feature.work.routine_selection.readyWorkSelectionScreen
import com.gradation.lift.navigation.Router.READY_WORK_GRAPH_NAME
import com.gradation.lift.navigation.Router.READY_WORK_SELECTION_ROUTER_NAME

@RequiresApi(Build.VERSION_CODES.O)
fun readyWorkGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
        navGraphBuilder.navigation(
            route = READY_WORK_GRAPH_NAME,
            startDestination = READY_WORK_SELECTION_ROUTER_NAME,
        ) {

            readyWorkSelectionScreen(navController, this)
            readyWorkChangeOrderScreen(navController, this)
        }
    }
