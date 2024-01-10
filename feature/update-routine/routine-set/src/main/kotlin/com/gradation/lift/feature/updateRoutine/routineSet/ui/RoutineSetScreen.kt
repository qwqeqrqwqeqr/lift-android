package com.gradation.lift.feature.updateRoutine.routineSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.EmptyRoutineView
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.NavigationView
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.RoutineSetView
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.RoutineView
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@Composable
internal fun RoutineSetScreen(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    updateCondition: Boolean,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    routineUiState: RoutineUiState,

    updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit,
    popBackStack: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph: (Int) -> Unit,
    routineSetScreenState: RoutineSetScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "루틴 수정",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = popBackStack,
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
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no5)
                        .padding(padding)
                ) {
                    Column(
                        modifier = modifier
                            .verticalScroll(routineSetScreenState.scrollState)
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
                    ) {
                        RoutineSetView(
                            modifier,
                            currentRoutineSetRoutine,
                            routineSetNameValidator,
                            routineSetDescriptionValidator,
                            currentRoutineSetRoutineState,
                            navigateRoutineSetToProfilePictureInUpdateRoutineGraph,
                            routineSetScreenState
                        )
                        if (currentRoutineSetRoutine.routine.isEmpty())
                            EmptyRoutineView(
                                modifier,
                                navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
                            )
                        else
                            RoutineView(
                                modifier,
                                currentRoutineSetRoutine,
                                currentRoutineSetRoutineState,
                                navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
                                navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph
                            )
                    }
                    Column {
                        NavigationView(
                            modifier,
                            updateCondition,
                            currentRoutineSetRoutine,
                            updateRoutineSetRoutine,
                            routineSetScreenState,
                        )
                    }
                }

            }
        }
    }
}




