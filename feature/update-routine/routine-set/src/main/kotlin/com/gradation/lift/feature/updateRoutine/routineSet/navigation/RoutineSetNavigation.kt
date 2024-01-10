package com.gradation.lift.feature.updateRoutine.routineSet.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToChangeOrderInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfilePictureInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateUpdateRoutineGraphToRoutineDetailGraph
import com.gradation.lift.navigation.navigation.navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter


fun NavGraphBuilder.routineSetScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {

        val popBackStack: () -> Unit = { navController.popBackStack() }

        val navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph() }

        val navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit =
            { navController.navigateRoutineSetToProfilePictureInUpdateRoutineGraph() }

        val navigateUpdateRoutineGraphToRoutineDetailGraph: () -> Unit = {
            navController.navigateUpdateRoutineGraphToRoutineDetailGraph()
        }
        val navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter: (Int) -> Unit = {
            navController.navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter(it)
        }
        val navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph:(Int) -> Unit ={
            navController.navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph(it)
        }

        val navigateRoutineSetToChangeOrderInUpdateRoutineGraph:() -> Unit = {
            navController.navigateRoutineSetToChangeOrderInUpdateRoutineGraph()
        }

        RoutineSetRoute(
            modifier,
            navController,
            popBackStack,
            navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
            navigateRoutineSetToProfilePictureInUpdateRoutineGraph,
            navigateUpdateRoutineGraphToRoutineDetailGraph,
            navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter,
            navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph,
            navigateRoutineSetToChangeOrderInUpdateRoutineGraph
        )
    }
}



