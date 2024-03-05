package com.gradation.lift.feature.createRotuine.updateWorkSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.NavigationView
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.RoutineListView
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.ui.extensions.focusClearManager


@Composable
internal fun UpdateWorkSetScreen(
    modifier: Modifier = Modifier,
    routineIndex: Int?,
    workSetState: WorkSetState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
    routineScreenState: RoutineScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = workSetState.workCategoryName,
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph,
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
                    .focusClearManager(focusManager = routineScreenState.focusManager)
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
                routineIndex,
                workSetState,
                currentRoutineSetRoutineState,
                navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph
            )
        }
    }
}
