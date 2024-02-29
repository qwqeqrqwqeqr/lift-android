package com.gradation.lift.designsystem.component.textField

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftAuthenticationInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    authenticationMessage: String,
    sendAuthenticationCode: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    isValid: Boolean = false,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()

    val textColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no4.copy(0.5f)
        else if (!enabled) LiftTheme.colorScheme.no6
        else LiftTheme.colorScheme.no4,
        label = "labelColor"
    )

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
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { sendAuthenticationCode() },
                    )
                    .padding(end = LiftTheme.space.space16),
                textStyle = LiftTextStyle.No8,
                text = authenticationMessage,
                color = textColor,
                textAlign = TextAlign.Start
            )
        }
    )
}