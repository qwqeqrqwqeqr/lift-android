package com.gradation.lift.feature.updateRoutine.routine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.routine.data.state.KeypadState
import com.gradation.lift.feature.updateRoutine.routine.data.state.KeypadWorkSetState
import com.gradation.lift.feature.updateRoutine.routine.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.routine.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.routine.ui.component.NavigationView
import com.gradation.lift.feature.updateRoutine.routine.ui.component.RoutineListView
import com.gradation.lift.feature.updateRoutine.routine.ui.component.WorkSetKeyPadBottomSheet


@Composable
fun RoutineScreen(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    routineUiState: RoutineUiState,
    workCategoryUiState: WorkCategoryUiState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    when (workCategoryUiState) {
        is WorkCategoryUiState.Fail -> {
        }

        WorkCategoryUiState.Loading -> {
        }

        is WorkCategoryUiState.Success -> {
            Scaffold(
                topBar = {
                    LiftBackTopBar(
                        title = workCategoryUiState.workCategory.name,
                        onBackClickTopBar = navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
                    )
                },
                modifier = modifier.fillMaxSize(),
            ) { padding ->
                Column(
                    modifier = modifier.padding(padding),
                ) {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .fillMaxSize()
                            .background(LiftTheme.colorScheme.no5)
                    ) {
                        RoutineListView(
                            modifier,
                            keypadWorkSetState,
                            workSetState,
                            keypadState
                        )
                    }
                    NavigationView(
                        modifier,
                        workCategoryUiState.workCategory,
                        workSetState,
                        routineUiState,
                        currentRoutineSetRoutineState,
                        navigateRoutineToRoutineSetInUpdateRoutineGraph
                    )
                }
                WorkSetKeyPadBottomSheet(
                    modifier,
                    keypadWorkSetState,
                    workSetState,
                    keypadState
                )
            }
        }
    }


}
