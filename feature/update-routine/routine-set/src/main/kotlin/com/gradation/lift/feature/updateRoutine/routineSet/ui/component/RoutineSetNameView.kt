package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun RoutineSetNameView(
    modifier: Modifier = Modifier,
    routineSetNameValidator: Validator,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    routineSetScreenState: RoutineSetScreenState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 이름",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        LiftDefaultInputTextField(
            value = currentRoutineSetRoutine.name,
            onValueChange = currentRoutineSetRoutineState.updateRoutineSetName,
            placeHolderValue = "이름을 입력해주세요 (1-10 자)",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    routineSetScreenState.focusManager.moveFocus(FocusDirection.Down)
                },
            ),
            onValueClear = currentRoutineSetRoutineState.clearRoutineSetName,
            isError = !routineSetNameValidator.status,
        )
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No7,
            text = routineSetNameValidator.message,
            color = LiftTheme.colorScheme.no12,
            textAlign = TextAlign.Start
        )
    }
}