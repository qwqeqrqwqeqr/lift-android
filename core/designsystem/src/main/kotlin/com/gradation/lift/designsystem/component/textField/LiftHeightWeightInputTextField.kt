package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftHeightInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    isValid: Boolean = false,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()

    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        isValid = isValid,
        enabled = enabled,
        isFocused = isFocused,
        interactionSource = interactionSource,
        trailingIcon = @Composable {
            LiftText(
                modifier = modifier
                    .padding(end = LiftTheme.space.space16),
                textStyle = LiftTextStyle.No8,
                text = "cm",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Start
            )
        }
    )
}

@Composable
fun LiftWeightInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    isValid: Boolean = false,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()

    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        isValid = isValid,
        enabled = enabled,
        isFocused = isFocused,
        interactionSource = interactionSource,
        trailingIcon = @Composable {
            LiftText(
                modifier = modifier
                    .padding(end = LiftTheme.space.space16),
                textStyle = LiftTextStyle.No8,
                text = "kg",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Start
            )
        }
    )
}