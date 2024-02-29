package com.gradation.lift.designsystem.component.chart.model


/**
 * [WorkPartCountByMonth]
 * 현재와 과거에 대한 운동 부위 별 운동 횟수
 * @since 2024-02-20 17:45:55
 */
data class WorkPartCountByMonth(
    val name: String,
    val currentCount: Int,
    val preCount: Int,
)