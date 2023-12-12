package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun RoutineSetView(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
    routineSetScreenState: RoutineSetScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        RoutineSetProfilePictureView(
            modifier,
            currentRoutineSetRoutine,
            navigateRoutineSetToProfilePictureInCreateRoutineGraph
        )


        RoutineSetNameView(
            modifier,
            routineSetNameValidator,
            currentRoutineSetRoutine,
            currentRoutineSetRoutineState,
            routineSetScreenState
        )
        RoutineSetDescriptionView(
            modifier,
            routineSetDescriptionValidator,
            currentRoutineSetRoutine,
            currentRoutineSetRoutineState,
            routineSetScreenState
        )

        RoutineSetWeekdayView(
            modifier,
            currentRoutineSetRoutine,
            currentRoutineSetRoutineState
        )

        RoutineSetLabelView(
            modifier,
            currentRoutineSetRoutine,
            currentRoutineSetRoutineState
        )


    }
}

