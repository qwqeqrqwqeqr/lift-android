package com.gradation.lift.feature.routine_detail.routine.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.routine_detail.routine.data.RoutineUiState
import com.gradation.lift.feature.routine_detail.routine.ui.RoutineScreen
import com.gradation.lift.feature.routine_detail.routine.data.RoutineViewModel
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

@Composable
fun RoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    popBackStack: () -> Unit,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit,
    viewModel: RoutineViewModel = hiltViewModel(),
) {
    val routineSetId: Int? = navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY)


    val routineUiState: RoutineUiState by viewModel.routineSetRoutine.collectAsStateWithLifecycle()

    RoutineScreen(
        modifier,
        routineUiState,
        popBackStack,
        navigateRoutineDetailGraphToUpdateRoutineGraph,
        navigateRoutineDetailGraphToWorkWorkRouter
    )

    LaunchedEffect(true) { viewModel.setRoutineSetId(routineSetId) }
    BackHandler(onBack = popBackStack)
}