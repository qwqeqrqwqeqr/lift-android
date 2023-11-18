package com.gradation.lift.feature.routine_detail.routine_list.data.state

/**
 * [All] 모든 라벨 조회
 * [Label] 특정 라벨 조회
 * @since 2023-11-18 17:22:11
 */
sealed interface LabelFilterType {
    data object All : LabelFilterType
    data class Label(val labelSet: Set<com.gradation.lift.model.model.routine.Label>) : LabelFilterType
}