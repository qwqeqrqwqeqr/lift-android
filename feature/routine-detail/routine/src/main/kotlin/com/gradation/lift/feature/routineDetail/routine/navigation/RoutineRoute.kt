package com.gradation.lift.feature.routineDetail.routine.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.routineDetail.routine.data.RoutineUiState
import com.gradation.lift.feature.routineDetail.routine.data.RoutineViewModel
import com.gradation.lift.feature.routineDetail.routine.ui.RoutineScreen

@Composable
fun RoutineRoute(
    modifier: Modifier = Modifier,
    navigateRoutineDetailGraphToBack: () -> Unit,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkReadyReadyRoute: (String) -> Unit,
    viewModel: RoutineViewModel = hiltViewModel(),
) {
    val routineUiState: RoutineUiState by viewModel.routineSetRoutine.collectAsStateWithLifecycle()

    RoutineScreen(
        modifier,
        routineUiState,
        navigateRoutineDetailGraphToBack,
        navigateRoutineDetailGraphToUpdateRoutineGraph,
        navigateRoutineDetailGraphToWorkReadyReadyRoute
    )

    BackHandler(onBack = navigateRoutineDetailGraphToBack)
}