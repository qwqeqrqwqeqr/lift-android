package com.gradation.lift.feature.routineDetail.routine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routineDetail.routine.data.RoutineUiState
import com.gradation.lift.feature.routineDetail.routine.ui.component.NavigationView
import com.gradation.lift.feature.routineDetail.routine.ui.component.RoutineContentView
import com.gradation.lift.feature.routineDetail.routine.ui.component.RoutineView


@Composable
fun RoutineScreen(
    modifier: Modifier = Modifier,
    routineUiState: RoutineUiState,
    navigateRoutineDetailGraphToBack: () -> Unit,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 상세",
                onBackClickTopBar = navigateRoutineDetailGraphToBack,
            )
        }
    ) { padding ->
        when (routineUiState) {
            is RoutineUiState.Fail -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            RoutineUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            is RoutineUiState.Success -> {
                Column(
                    modifier = modifier.padding(padding)
                ) {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        RoutineContentView(modifier, routineUiState.routineSetRoutine)
                        RoutineView(modifier, routineUiState.routineSetRoutine)
                    }
                    NavigationView(
                        modifier,
                        routineUiState.routineSetRoutine,
                        navigateRoutineDetailGraphToUpdateRoutineGraph,
                        navigateRoutineDetailGraphToWorkWorkRouter
                    )
                }
            }
        }
    }
}




