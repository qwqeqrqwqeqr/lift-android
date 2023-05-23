package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LiftDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier,
        thickness = 1.0.dp,
        color = MaterialTheme.colorScheme.outline)
}