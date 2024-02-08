package com.gradation.lift.designsystem.component.textField

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftSearchInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    onValueClear: (() -> Unit)? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()


    LiftBaseInputTextField(
        modifier = modifier,
        value = value,
        placeHolderValue = placeHolderValue,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled,
        isError = false,
        isValid = false,
        isFocused = isFocused,
        interactionSource = interactionSource,
        trailingIcon = @Composable {
            Row(
                modifier = modifier.padding(end = LiftTheme.space.space16),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(visible = value.isNotEmpty() && enabled && onValueClear != null) {
                    Icon(
                        modifier = modifier
                            .size(LiftTheme.space.space24)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = onValueClear!!,
                            ),
                            painter = painterResource(id = LiftIcon.Cancel),
                            contentDescription = "Clear",
                            tint = Color.Unspecified
                        )
                    }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space24),
                    painter = painterResource(id = LiftIcon.Search),
                    contentDescription = "VisualTransformation",
                    tint = LiftTheme.colorScheme.no6
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = LiftTheme.colorScheme.no6,
            focusedContainerColor = LiftTheme.colorScheme.no1,
            focusedPlaceholderColor = LiftTheme.colorScheme.no6,
            cursorColor = LiftTheme.colorScheme.no4,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = LiftTheme.colorScheme.no6,
            unfocusedLabelColor = LiftTheme.colorScheme.no6,
            unfocusedContainerColor = LiftTheme.colorScheme.no1,
            unfocusedPlaceholderColor = LiftTheme.colorScheme.no6,
            unfocusedIndicatorColor = Color.Transparent,
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
                backgroundColor = LiftTheme.colorScheme.no6.copy(alpha = 0.2f),
            )
        ),
    )
}
