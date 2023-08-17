package com.gradation.lift.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTextField(
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

    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = LiftTheme.colorScheme.no9,
        containerColor = LiftTheme.colorScheme.no1,
        placeholderColor = LiftTheme.colorScheme.no9,
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
    TextField(
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftSearchTextField(
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
    isError: Boolean = false,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = LiftTheme.typography.no6,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = placeholder,
        colors = TextFieldDefaults.textFieldColors(
            textColor = LiftTheme.colorScheme.no9,
            containerColor = LiftTheme.colorScheme.no1,
            placeholderColor = LiftTheme.colorScheme.no9,
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
        shape = RoundedCornerShape(12.dp),
        visualTransformation = visualTransformation,
        trailingIcon = {
            Icon(
                painter = painterResource(LiftIcon.Search),
                contentDescription = "",
                tint = LiftTheme.colorScheme.no6,
            )
        },
        supportingText = supportingText,
        isError = isError
    )
}


@Preview
@Composable
fun LiftTextFieldPreview() {
    LiftMaterialTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LiftTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "힌트",
                        style = LiftTheme.typography.no6
                    )
                },
            )
            LiftSearchTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "검색",
                        style = LiftTheme.typography.no6
                    )
                },
            )


        }

    }
}