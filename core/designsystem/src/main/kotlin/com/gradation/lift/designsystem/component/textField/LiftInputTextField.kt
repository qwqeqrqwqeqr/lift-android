package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
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
) {

    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
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
    )
}

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
        modifier,
        value,
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




@Composable
fun LiftBaseInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    isValid: Boolean = false,
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = LiftTheme.typography.no6,
        enabled = enabled,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = @Composable {
            LiftText(
                modifier = modifier,
                textStyle = LiftTextStyle.No6,
                text = placeHolderValue,
                color = LiftTheme.colorScheme.no6,
                textAlign = TextAlign.Start
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = LiftTheme.colorScheme.no6,
            focusedContainerColor = LiftTheme.colorScheme.no1,
            focusedPlaceholderColor = LiftTheme.colorScheme.no6,
            cursorColor = if (isValid) LiftTheme.colorScheme.no46 else LiftTheme.colorScheme.no4,
            focusedIndicatorColor = if (isValid) LiftTheme.colorScheme.no46 else LiftTheme.colorScheme.no4,
            unfocusedTextColor = LiftTheme.colorScheme.no6,
            unfocusedLabelColor = LiftTheme.colorScheme.no6,
            unfocusedContainerColor = LiftTheme.colorScheme.no1,
            unfocusedPlaceholderColor = LiftTheme.colorScheme.no6,
            unfocusedIndicatorColor = if (isValid) LiftTheme.colorScheme.no46 else Color.Transparent,
            disabledTextColor = LiftTheme.colorScheme.no6,
            disabledIndicatorColor = LiftTheme.colorScheme.no8,
            disabledContainerColor = LiftTheme.colorScheme.no1,
            disabledPlaceholderColor = LiftTheme.colorScheme.no6,
            errorLabelColor = LiftTheme.colorScheme.no12,
            errorTextColor = LiftTheme.colorScheme.no6,
            errorIndicatorColor = LiftTheme.colorScheme.no12,
            errorContainerColor = LiftTheme.colorScheme.no1,
            errorSupportingTextColor = LiftTheme.colorScheme.no12,
            errorCursorColor = LiftTheme.colorScheme.no12,
            selectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor =
                if (isValid) LiftTheme.colorScheme.no46.copy(alpha = 0.2f)
                else if (isError) LiftTheme.colorScheme.no12.copy(alpha = 0.2f)
                else LiftTheme.colorScheme.no4.copy(alpha = 0.2f),
            )
        ),
        shape = RoundedCornerShape(LiftTheme.space.space12),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        isError = isError
    )

}


