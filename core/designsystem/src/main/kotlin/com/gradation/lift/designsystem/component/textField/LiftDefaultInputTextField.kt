package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftDefaultInputTextField(
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
    singleLine: Boolean =true
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused: Boolean by interactionSource.collectIsHoveredAsState()


    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        isValid = isValid,
        singleLine = singleLine,
        enabled = enabled,
        isFocused = isFocused,
        interactionSource = interactionSource,
        trailingIcon = @Composable {
            if (isValid) {
                Icon(
                    modifier = modifier.size(LiftTheme.space.space24),
                    painter = painterResource(id = LiftIcon.CheckboxGreen),
                    contentDescription = "Valid",
                    tint = Color.Unspecified
                )
            }
            if (!isValid && value.isNotEmpty() && enabled && onValueClear != null) {
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space24)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onValueClear,
                        ),
                    painter = painterResource(id = LiftIcon.Cancel),
                    contentDescription = "Clear",
                    tint = Color.Unspecified
                )
            }
        }
    )
}
