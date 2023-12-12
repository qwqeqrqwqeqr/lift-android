package com.gradation.lift.feature.register_detail.height_weight.component

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
internal fun HeightTextFieldView(
    modifier: Modifier = Modifier,
    heightText: String,
    heightValidator: Validator,
    updateHeightText: (String) -> Unit,
    focusManager: FocusManager,
) {
    Text(
        text = "키",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = heightText,
        onValueChange = updateHeightText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "키를 입력해주세요.",
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9.copy(alpha = 0.7f),
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                updateHeightText(
                    heightText.toFloatOrNull()?.toString() ?: "175.0"
                )
                focusManager.moveFocus(FocusDirection.Down)
            },
        ),
        trailingIcon = {
            Text(
                text = "cm",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no9
            )
        }
    )
    if (!heightValidator.status) {
        Text(
            text = heightValidator.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    } else {
        Text(
            text = heightValidator.message,
            style = LiftTheme.typography.no7,
            color = Color.Transparent
        )
    }
}

