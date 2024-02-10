package com.gradation.lift.designsystem.temp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor:Color =LiftTheme.colorScheme.no4,
    contentColor:Color =LiftTheme.colorScheme.no5,
    shape: RoundedCornerShape = RoundedCornerShape(size = 12.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = LiftTheme.colorScheme.no13,
            disabledContentColor = LiftTheme.colorScheme.no5
        ),
        contentPadding = contentPadding,
        content = content,
    )
}


@Composable
fun LiftErrorButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = RoundedCornerShape(size = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no12,
            contentColor = LiftTheme.colorScheme.no21,
            disabledContainerColor = LiftTheme.colorScheme.no13,
            disabledContentColor = LiftTheme.colorScheme.no5
        ),
        contentPadding = contentPadding,
        content = content,
    )
}


@Composable
fun LiftCancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = RoundedCornerShape(size = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no13,
            contentColor = LiftTheme.colorScheme.no5,
            disabledContainerColor = LiftTheme.colorScheme.no13,
            disabledContentColor = LiftTheme.colorScheme.no5
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun LiftOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(size = 6.dp),
    border :Dp = 1.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no5,
            contentColor = LiftTheme.colorScheme.no4,
            disabledContainerColor = LiftTheme.colorScheme.no13,
            disabledContentColor = LiftTheme.colorScheme.no5
        ),
        border = BorderStroke(
            width = border,
            color = if (enabled) LiftTheme.colorScheme.no4 else Color.Transparent
        ),
        contentPadding = contentPadding,
        content = content,
    )
}


@Composable
fun LiftIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = LiftTheme.colorScheme.no9,
        ),
        content = content

    )
}

