package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun RoutineSetDescriptionView(
    modifier: Modifier = Modifier,
    routineSetDescriptionValidator: Validator,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    routineSetScreenState: RoutineSetScreenState
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        Text(
            text = "루틴 설명",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no3
        )
        LiftTextField(
            value = currentRoutineSetRoutine.description,
            onValueChange = currentRoutineSetRoutineState.updateRoutineSetDescription,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "간단한 설명을 입력해주세요 (0 - 20 자)",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no9.copy(alpha = 0.7f)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                routineSetScreenState.focusManager.clearFocus()
            }),
            singleLine = true,
        )
    }
}