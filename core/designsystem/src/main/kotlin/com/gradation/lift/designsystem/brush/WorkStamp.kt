package com.gradation.lift.designsystem.brush

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun workStampBrush(): Brush =
    Brush.linearGradient(
        colors = listOf(LiftTheme.colorScheme.no54, LiftTheme.colorScheme.no55),
        start = Offset.Zero,
        end = Offset.Infinite
    )
