package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.register_detail.gender.registerDetailGenderScreen
import com.gradation.lift.feature.register_detail.height_weight.registerDetailHeightWeightScreen
import com.gradation.lift.feature.work.change_order.workChangeOrderScreen
import com.gradation.lift.feature.work.complete.workCompleteScreen
import com.gradation.lift.feature.work.routine_selection.workRoutineSelectionScreen
import com.gradation.lift.feature.work.work.workWorkScreen
import com.gradation.lift.navigation.Router.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.Router.WORK_WORK_ROUTER_NAME

@RequiresApi(Build.VERSION_CODES.O)
fun workGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = WORK_GRAPH_NAME,
        startDestination = WORK_ROUTINE_SELECTION_ROUTER_NAME,
    ) {
        workRoutineSelectionScreen(navController, this)
        workChangeOrderScreen(navController, this)
        workWorkScreen(navController, this)
        workCompleteScreen(navController, this)
    }
}