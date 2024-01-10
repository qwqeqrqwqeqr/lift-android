package com.gradation.lift.feature.createRotuine.updateWorkSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.KeypadState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.KeypadWorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.NavigationView
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.RoutineListView
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.WorkSetKeyPadBottomSheet
import com.gradation.lift.designsystem.component.topBar.LiftTopBar


@Composable
internal fun UpdateWorkSetScreen(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    workCategoryUiState: WorkCategoryUiState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateCreateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
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
                        onClick = navigateCreateWorkSetToFindWorkCategoryInCreateRoutineGraph,
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
                        currentRoutineSetRoutineState,
                        navigateCreateWorkSetToRoutineSetInCreateRoutineGraph
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
