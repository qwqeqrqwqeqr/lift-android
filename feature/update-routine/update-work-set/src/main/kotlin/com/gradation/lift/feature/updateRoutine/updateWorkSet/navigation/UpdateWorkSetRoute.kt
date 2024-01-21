package com.gradation.lift.feature.updateRoutine.updateWorkSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.UpdateWorkSetViewModel
import com.gradation.lift.feature.updateRoutine.common.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.rememberRoutineScreenState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.ui.UpdateWorkSetScreen
import com.gradation.lift.navigation.Route

@Composable
@SuppressLint("UnrememberedGetBackStackEntry")
internal fun UpdateWorkSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    routineIndex: Int?,
    navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph: () -> Unit,
    viewModel: UpdateWorkSetViewModel = hiltViewModel(),
    sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.UPDATE_ROUTINE_GRAPH_NAME) }),
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

    BackHandler(onBack = navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph)

    UpdateWorkSetScreen(
        modifier,
        routineIndex,
        workSetState,
        currentRoutineSetRoutineState,
        navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph,
        routineScreenState
    )


}