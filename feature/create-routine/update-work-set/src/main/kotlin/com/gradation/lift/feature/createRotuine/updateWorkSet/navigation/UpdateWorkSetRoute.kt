package com.gradation.lift.feature.createRotuine.updateWorkSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.feature.createRoutine.common.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.UpdateWorkSetViewModel
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.UpdateWorkSetScreen
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun UpdateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    routineIndex: Int?,
    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
    viewModel: UpdateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.CREATE_ROUTINE_GRAPH_NAME) }),
    routineScreenState: RoutineScreenState = rememberRoutineScreenState(),
) {

    LaunchedEffect(Unit) {
        viewModel.setRoutine(
            sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.value.routine[routineIndex!!]
        )
    }




    val workSetState: WorkSetState = viewModel.workSetState
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState

    BackHandler(onBack = navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph)

    UpdateWorkSetScreen(
        modifier,
        routineIndex,
        workSetState,
        currentRoutineSetRoutineState,
        navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph,
        routineScreenState
    )


}