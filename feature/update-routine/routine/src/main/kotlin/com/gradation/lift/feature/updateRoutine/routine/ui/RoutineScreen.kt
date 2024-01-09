package com.gradation.lift.feature.updateRoutine.routine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.routine.data.state.KeypadState
import com.gradation.lift.feature.updateRoutine.routine.data.state.KeypadWorkSetState
import com.gradation.lift.feature.updateRoutine.routine.data.state.RoutineScreenState
import com.gradation.lift.feature.updateRoutine.routine.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.routine.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.routine.ui.component.NavigationView
import com.gradation.lift.feature.updateRoutine.routine.ui.component.RoutineListView
import com.gradation.lift.feature.updateRoutine.routine.ui.component.WorkSetKeyPadBottomSheet


@Composable
internal fun RoutineScreen(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    routineUiState: RoutineUiState,
    workCategoryUiState: WorkCategoryUiState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit,
    routineScreenState: RoutineScreenState
) {
    when (workCategoryUiState) {
        is WorkCategoryUiState.Fail -> {
            Spacer(modifier = modifier.fillMaxSize().background(LiftTheme.colorScheme.no17))
        }

        WorkCategoryUiState.Loading -> {
            Spacer(modifier = modifier.fillMaxSize().background(LiftTheme.colorScheme.no17))
        }

        is WorkCategoryUiState.Success -> {
            Scaffold(
                topBar = {
                    LiftTopBar(
                        title = workCategoryUiState.workCategory.name,
                        backgroundColor = LiftTheme.colorScheme.no5,
                        onClick = navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
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
                            keypadState,
                            routineScreenState
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
