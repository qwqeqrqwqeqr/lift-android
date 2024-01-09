package com.gradation.lift.feature.createRoutine.routineSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.createRoutine.routineSet.ui.component.EmptyRoutineView
import com.gradation.lift.feature.createRoutine.routineSet.ui.component.NavigationView
import com.gradation.lift.feature.createRoutine.routineSet.ui.component.RoutineSetView
import com.gradation.lift.feature.createRoutine.routineSet.ui.component.RoutineView
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@Composable
internal fun RoutineSetScreen(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    updateCondition: Boolean,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
    navigateCreateRoutineGraphToRoutineDetailGraph: () -> Unit,
    routineSetScreenState: RoutineSetScreenState,
) {

    Scaffold(
        topBar = {
            LiftTopBar(
                title = "루틴 만들기",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = {
                    if (currentRoutineSetRoutine.routine.isEmpty()) {
                        navigateCreateRoutineGraphToRoutineDetailGraph()
                    } else {
                        routineSetScreenState.updateCancelDialogView(true)
                    }
                }
            )
        }
    ) { padding ->

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
                    navigateRoutineSetToProfilePictureInCreateRoutineGraph,
                    routineSetScreenState
                )
                if (currentRoutineSetRoutine.routine.isEmpty())
                    EmptyRoutineView(
                        modifier,
                        navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
                    )
                else
                    RoutineView(
                        modifier,
                        currentRoutineSetRoutine,
                        currentRoutineSetRoutineState,
                        navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
                    )
            }
            Column {
                NavigationView(
                    modifier,
                    updateCondition,
                    currentRoutineSetRoutineState,
                    routineSetScreenState
                )
            }
        }
    }
}




