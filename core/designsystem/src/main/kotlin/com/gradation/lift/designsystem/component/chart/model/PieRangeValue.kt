package com.gradation.lift.designsystem.component.chart.model

import androidx.compose.ui.graphics.Color

data class PieChartValue(
    val value: WorkCategoryCount,
    val startPosition: Float,
    val rate: Float,
    val stroke: Float,
    val shadowStroke: Float,
    val visibleBubbleView: Boolean,
    val color: Color,
)


