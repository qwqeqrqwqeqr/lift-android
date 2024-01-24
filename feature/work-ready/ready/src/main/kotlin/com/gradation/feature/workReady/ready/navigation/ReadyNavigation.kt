package com.gradation.feature.workReady.ready.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.WORK_READY_READY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateReadyToFindWorkCategoryInWorkReadyGraph
import com.gradation.lift.navigation.navigation.navigateWorkReadyGraphToWorkGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle


fun NavGraphBuilder.readyScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val popBackStack: () -> Unit = { navController.popBackStack() }
    val navigateReadyToFindWorkCategoryInWorkReadyGraph: () -> Unit =
        { navController.navigateReadyToFindWorkCategoryInWorkReadyGraph() }

    val navigateWorkReadyGraphToWorkGraph: () -> Unit =
        { navController.navigateWorkReadyGraphToWorkGraph() }

    composable(
        route = WORK_READY_READY_ROUTER_NAME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        val routineSetIdList =
            navController.getValueSavedStateHandle<String>(WORK_ROUTINE_SET_ID_LIST_KEY)?.split("|")
                ?.map { it.toInt() }
                ?.toSet()
                ?: emptySet<Int>()

        ReadyRoute(
            modifier,
            navController,
            routineSetIdList,
            popBackStack,
            navigateReadyToFindWorkCategoryInWorkReadyGraph,
            navigateWorkReadyGraphToWorkGraph
        )
    }
}


