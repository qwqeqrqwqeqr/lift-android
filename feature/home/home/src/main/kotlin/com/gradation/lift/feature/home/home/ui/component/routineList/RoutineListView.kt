package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.home.home.data.state.RoutineUiState

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routineUiState: RoutineUiState,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
) {
    when (routineUiState) {
        is RoutineUiState.Fail -> {
            FailRoutineListView(
                modifier,
                navigateHomeGraphToRoutineDetailGraph
            )
        }

        RoutineUiState.Empty -> {
            EmptyRoutineListView(
                modifier,
                navigateHomeGraphToRoutineDetailGraph,
                navigateMainGraphToCreateRoutineGraph
            )
        }

        RoutineUiState.Loading -> {
            LoadingRoutineListView(
                modifier,
                navigateHomeGraphToRoutineDetailGraph,
                navigateMainGraphToCreateRoutineGraph
            )
        }

        is RoutineUiState.Success -> {
            SuccessRoutineListView(
                modifier,
                routineUiState.routineState.routineList,
                navigateHomeGraphToRoutineDetailGraph,
                navigateMainGraphToCreateRoutineGraph,
                navigateHomeGraphToRoutineDetailRoutineRouter,
            )

        }
    }
}