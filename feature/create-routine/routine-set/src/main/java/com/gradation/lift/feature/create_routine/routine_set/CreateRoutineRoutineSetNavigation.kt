package com.gradation.lift.feature.create_routine.routine_set

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateRoutineSetToProfileInCreateRoutineGraph
import com.gradation.lift.navigation.navigation.navigateCreateRoutineGraphToHomeGraph


@RequiresApi(Build.VERSION_CODES.O)
fun createRoutineScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: ()->Unit =
        { navController.navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() }
    val navigateRoutineSetToProfileInCreateRoutineGraph: ()->Unit = { navController.navigateRoutineSetToProfileInCreateRoutineGraph() }
    val navigateCreateRoutineGraphToHomeGraph: ()->Unit = { navController.navigateCreateRoutineGraphToHomeGraph() }

    navGraphBuilder.composable(CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME) {
        CreateRoutineRoutineSetRoute(
            navController = navController,
            navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph=navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
            navigateRoutineSetToProfileInCreateRoutineGraph=navigateRoutineSetToProfileInCreateRoutineGraph,
            navigateCreateRoutineGraphToHomeGraph=navigateCreateRoutineGraphToHomeGraph
        )
    }
}

