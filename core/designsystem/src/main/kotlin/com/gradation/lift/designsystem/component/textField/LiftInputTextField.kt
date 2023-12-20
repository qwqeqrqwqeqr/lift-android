package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun TestTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    onValueClear: (() -> Unit)? = null,
    enabled: Boolean = true,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    isValid: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(
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
        colors = colors,
        shape = RoundedCornerShape(12.dp),
        label = label,
        visualTransformation = visualTransformation,
        trailingIcon =
        @Composable {
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
        },
        supportingText = supportingText,
        isError = isError
    )

}


@Composable
fun LiftPasswordInputTextField() {

}

@Composable
fun LiftSearchInputTextField() {

}