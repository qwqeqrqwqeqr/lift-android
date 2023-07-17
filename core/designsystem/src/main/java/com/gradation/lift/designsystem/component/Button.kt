package com.gradation.lift.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftButton(
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
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no4,
            contentColor = LiftTheme.colorScheme.no5,
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
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no5,
            contentColor = LiftTheme.colorScheme.no4,
        ),
        border= BorderStroke(
            width= 2.dp,
            color= LiftTheme.colorScheme.no4
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
        modifier =  modifier.height(48.dp),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor =LiftTheme.colorScheme.no9,
        ),
        content = content

    )
}

