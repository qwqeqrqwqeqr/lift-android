package com.gradation.lift.feature.update_routine.find_work_category

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import androidx.navigation.NavController
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph


@RequiresApi(Build.VERSION_CODES.O)
fun updateRoutineFindWorkCategoryScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph() }

    val navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: () -> Unit =
        { navController.navigateFindWorkCategoryToRoutineInUpdateRoutineGraph() }

    navGraphBuilder.composable(UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME) {
        UpdateRoutineFindWorkCategoryRoute(
            navController = navController,
            navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph=navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph,
            navigateFindWorkCategoryToRoutineInUpdateRoutineGraph=navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
        )
    }

}
