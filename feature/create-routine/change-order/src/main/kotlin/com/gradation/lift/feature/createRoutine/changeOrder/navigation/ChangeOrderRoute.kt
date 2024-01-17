package com.gradation.lift.feature.createRoutine.changeOrder.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.createRoutine.changeOrder.ui.ChangeOrderScreen
import com.gradation.lift.feature.createRoutine.common.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Route

@Composable
internal fun ChangeOrderRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateChangeOrderToRoutineSetInCreateRoutineGraph: () -> Unit,
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: CreateRoutineSharedViewModel =
        hiltViewModel(remember { navController.getBackStackEntry(Route.CREATE_ROUTINE_GRAPH_NAME) }),
) {
    val currentRoutineSetRoutine: RoutineSetRoutine by sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.collectAsStateWithLifecycle()
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState


    BackHandler(onBack = navigateChangeOrderToRoutineSetInCreateRoutineGraph)

    ChangeOrderScreen(
        modifier,
        currentRoutineSetRoutine,
        currentRoutineSetRoutineState,
        navigateChangeOrderToRoutineSetInCreateRoutineGraph
    )
}