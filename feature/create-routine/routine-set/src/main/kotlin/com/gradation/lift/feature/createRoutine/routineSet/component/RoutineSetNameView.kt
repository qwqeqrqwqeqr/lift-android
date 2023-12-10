package com.gradation.lift.feature.createRoutine.routineSet.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RoutineSetNameView(
    modifier: Modifier = Modifier,
    routineSetName: String,
    routineSetNameValidator: Validator,
    updateRoutineSetName: (String) -> Unit,
    focusManager: FocusManager,
) {
    Text(
        text = "루틴 이름",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = routineSetName,
        onValueChange = updateRoutineSetName,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "이름을 입력해주세요 (1-10 자)",
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9.copy(alpha = 0.7f)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        singleLine = true,
    )
    if (!routineSetNameValidator.status) {
        Text(
            text = routineSetNameValidator.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    }else{
        Text(
            text = routineSetNameValidator.message,
            style = LiftTheme.typography.no7,
            color = Color.Transparent
        )
    }
}