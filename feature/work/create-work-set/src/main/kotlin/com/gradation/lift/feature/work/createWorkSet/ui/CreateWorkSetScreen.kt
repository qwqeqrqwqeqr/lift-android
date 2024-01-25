package com.gradation.lift.feature.work.createWorkSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.work.createWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.work.createWorkSet.ui.component.NavigationView
import com.gradation.lift.feature.work.createWorkSet.ui.component.RoutineListView
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.feature.work.common.data.WorkState


@Composable
internal fun CreateWorkSetScreen(
    modifier: Modifier = Modifier,
    workCategoryUiState: WorkCategoryUiState,
    workSetState: WorkSetState,
    workState: WorkState,
    navigateCreateWorkSetToFindWorkCategoryInWorkGraph: () -> Unit,
    navigateCreateWorkSetToWorkInWorkGraph: () -> Unit,
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
                        onClick = navigateCreateWorkSetToFindWorkCategoryInWorkGraph,
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
                            workSetState,
                            routineScreenState
                        )
                    }
                    NavigationView(
                        modifier,
                        workCategoryUiState.workCategory,
                        workSetState,
                        workState,
                        navigateCreateWorkSetToWorkInWorkGraph
                    )
                }
            }
        }
    }
}