package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftPasswordInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    onValueClear: (() -> Unit)? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    isValid: Boolean = false,

    ) {
    var visualTransformation: Boolean by remember { mutableStateOf(true) }
    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        enabled = enabled,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        isError = isError,
        isValid = isValid,
        trailingIcon = @Composable {
            if (isValid) {
                Icon(
                    modifier = modifier.size(LiftTheme.space.space24),
                    painter = painterResource(id = LiftIcon.GreenCheck),
                    contentDescription = "Valid",
                    tint = Color.Unspecified
                )
            }
            if (!isValid && value.isNotEmpty() && enabled && onValueClear != null) {
                Row(
                    modifier = modifier.padding(end = LiftTheme.space.space16),
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = modifier
                            .size(LiftTheme.space.space24)
                            .clickable { visualTransformation = !visualTransformation },
                        painter = if (visualTransformation) painterResource(id = LiftIcon.EyeSelected) else painterResource(
                            id = LiftIcon.EyeUnSelected
                        ),
                        contentDescription = "VisualTransformation",
                        tint = Color.Unspecified
                    )

                    Icon(
                        modifier = modifier
                            .size(LiftTheme.space.space24)
                            .clickable { onValueClear() },
                        painter = painterResource(id = LiftIcon.Cancel),
                        contentDescription = "Clear",
                        tint = Color.Unspecified
                    )
                }

            }
        }
    )
}