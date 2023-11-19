package com.gradation.lift.designsystem.resource

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class LiftSpace(
    val space1: Dp = 1.dp,
    val space2: Dp = 2.dp,
    val space4: Dp = 4.dp,
    val space8: Dp = 8.dp,
    val space16: Dp = 16.dp,
    val space20: Dp = 20.dp,
    val space24: Dp = 24.dp,
    val space32: Dp = 32.dp,
    val space36: Dp = 36.dp,
    val space40: Dp = 40.dp,
    val space42: Dp = 42.dp,
    val space48: Dp = 48.dp,
    val space56: Dp = 56.dp,
    val space64: Dp = 64.dp,
    val space72: Dp = 72.dp,
    val space76: Dp = 76.dp,
    val space80: Dp = 80.dp,
    val space214: Dp = 214.dp,


    val horizontalPaddingSpace: Dp = 20.dp,
)


internal val LocalLiftSpace = staticCompositionLocalOf { LiftSpace() }
