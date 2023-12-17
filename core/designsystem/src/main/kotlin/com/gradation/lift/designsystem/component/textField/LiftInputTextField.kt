package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun TestTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()


    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        enabled=enabled
    )
}

@Composable
fun LiftInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    placeholder: @Composable (() -> Unit)?,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    isError: Boolean = false,
    textStyle: TextStyle = LiftTheme.typography.no6,

    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = LiftTheme.colorScheme.no9,
        focusedContainerColor = LiftTheme.colorScheme.no4,
        focusedPlaceholderColor = LiftTheme.colorScheme.no9,
        unfocusedLabelColor = LiftTheme.colorScheme.no9,
        unfocusedContainerColor = LiftTheme.colorScheme.no1,
        unfocusedPlaceholderColor = LiftTheme.colorScheme.no9,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
        errorLabelColor = LiftTheme.colorScheme.no12,
        errorSupportingTextColor = LiftTheme.colorScheme.no12,
        cursorColor = LiftTheme.colorScheme.no9,
        selectionColors = TextSelectionColors(
            handleColor = Color.Transparent,
            backgroundColor = LiftTheme.colorScheme.no9.copy(alpha = 0.3f),
        )
    ),
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = placeholder,
        colors = colors,
        shape = shape,
        label = label,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
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