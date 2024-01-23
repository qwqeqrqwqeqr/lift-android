package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftKeyPadTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    isError: Boolean,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = LiftTheme.colorScheme.no9,
        focusedContainerColor = LiftTheme.colorScheme.no5,
        focusedPlaceholderColor = LiftTheme.colorScheme.no9,
        cursorColor = LiftTheme.colorScheme.no4,
        focusedIndicatorColor = LiftTheme.colorScheme.no4,
        unfocusedTextColor = LiftTheme.colorScheme.no9,
        unfocusedLabelColor = LiftTheme.colorScheme.no9,
        unfocusedContainerColor = LiftTheme.colorScheme.no5,
        unfocusedPlaceholderColor = LiftTheme.colorScheme.no9,
        unfocusedIndicatorColor = Color.Transparent,
        disabledTextColor = LiftTheme.colorScheme.no6,
        disabledIndicatorColor = LiftTheme.colorScheme.no8,
        disabledContainerColor = LiftTheme.colorScheme.no1,
        disabledPlaceholderColor = LiftTheme.colorScheme.no6,
        errorLabelColor = LiftTheme.colorScheme.no12,
        errorTextColor = LiftTheme.colorScheme.no6,
        errorIndicatorColor = LiftTheme.colorScheme.no12,
        errorContainerColor = LiftTheme.colorScheme.no5,
        errorSupportingTextColor = LiftTheme.colorScheme.no12,
        errorCursorColor = LiftTheme.colorScheme.no12,
        selectionColors = TextSelectionColors(
            handleColor = Color.Transparent,
            backgroundColor = LiftTheme.colorScheme.no4.copy(alpha = 0.2f),
        )
    ),
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
        BasicTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            textStyle = LiftTheme.typography.no3.copy(textAlign = TextAlign.Center),
            enabled = enabled,
            singleLine = true,
            cursorBrush = SolidColor(colors.cursorColor),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = innerTextField,
                    placeholder = null,
                    label = null,
                    leadingIcon = null,
                    trailingIcon = null,
                    prefix = null,
                    suffix = null,
                    supportingText = null,
                    singleLine = true,
                    enabled = enabled,
                    contentPadding = PaddingValues(
                        start = LiftTheme.space.space16,
                        end = LiftTheme.space.space16,
                    ),
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled,
                            isError,
                            interactionSource,
                            colors,
                            RoundedCornerShape(LiftTheme.space.space8)
                        )
                    }
                )
            }
        )
    }
}
