package com.gradation.lift.feature.my_info.update.data.component

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
internal fun WeightTextFieldView(
    modifier: Modifier = Modifier,
    weightText: String,
    weightValidator: Validator,
    updateWeightText: (String) -> Unit,
    focusManager: FocusManager,
) {
    Text(
        text = "몸무게",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = weightText,
        onValueChange = updateWeightText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "몸무게를 입력해주세요.",
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9.copy(alpha = 0.7f),
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                updateWeightText(weightText.toFloatOrNull()?.toString() ?: "75.0")
                focusManager.clearFocus()
            },
        ),
        trailingIcon = {
            Text(
                text = "kg",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no9
            )
        }
    )
    if (!weightValidator.status) {
        Text(
            text = weightValidator.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    } else {
        Text(
            text = weightValidator.message,
            style = LiftTheme.typography.no7,
            color = Color.Transparent
        )
    }
}