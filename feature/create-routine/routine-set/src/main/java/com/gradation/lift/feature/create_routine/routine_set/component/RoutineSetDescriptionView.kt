package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RoutineSetDescriptionView(
    modifier: Modifier = Modifier,
    routineSetDescription: String,
    routineSetDescriptionValidator: Validator,
    updateRoutineSetDescription: (String) -> Unit,
    focusManager: FocusManager,
) {
    Text(
        text = "루틴 설명",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = routineSetDescription,
        onValueChange = updateRoutineSetDescription,
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
            focusManager.clearFocus()
        }),
        singleLine = true,
    )
    if (!routineSetDescriptionValidator.status) {
        Text(
            text = routineSetDescriptionValidator.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    }else{
        Text(
            text = routineSetDescriptionValidator.message,
            style = LiftTheme.typography.no7,
            color = Color.Transparent
        )
    }
}