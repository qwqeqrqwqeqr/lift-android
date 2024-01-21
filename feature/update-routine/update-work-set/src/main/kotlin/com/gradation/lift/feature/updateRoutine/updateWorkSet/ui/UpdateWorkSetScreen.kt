package com.gradation.lift.feature.updateRoutine.updateWorkSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.updateRoutine.updateWorkSet.ui.component.NavigationView
import com.gradation.lift.feature.updateRoutine.updateWorkSet.ui.component.RoutineListView
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState


@Composable
internal fun UpdateWorkSetScreen(
    modifier: Modifier = Modifier,
    routineIndex: Int?,
    workSetState: WorkSetState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph: () -> Unit,
    routineScreenState: RoutineScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = workSetState.workCategory?.name ?: "",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph,
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
                routineIndex,
                workSetState,
                currentRoutineSetRoutineState,
                navigateUpdateWorkSetToRoutineSetInUpdateRoutineGraph
            )
        }

    }
}
