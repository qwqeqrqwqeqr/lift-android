package com.gradation.lift.feature.routineDetail.routine.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.routineDetail.routine.data.RoutineUiState
import com.gradation.lift.feature.routineDetail.routine.ui.RoutineScreen
import com.gradation.lift.feature.routineDetail.routine.data.RoutineViewModel
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

@Composable
fun RoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineDetailGraphToBack: () -> Unit,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkReadyReadyRoute: (String) -> Unit,
    viewModel: RoutineViewModel = hiltViewModel(),
) {
    val routineSetId: Int? = navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.RoutineSet.DETAIL_ROUTINE_SET_ID_KEY)
    val routineUiState: RoutineUiState by viewModel.routineSetRoutine.collectAsStateWithLifecycle()

    RoutineScreen(
        modifier,
        routineUiState,
        navigateRoutineDetailGraphToBack,
        navigateRoutineDetailGraphToUpdateRoutineGraph,
        navigateRoutineDetailGraphToWorkReadyReadyRoute
    )

    LaunchedEffect(true) { viewModel.setRoutineSetId(routineSetId) }
    BackHandler(onBack = navigateRoutineDetailGraphToBack)
}