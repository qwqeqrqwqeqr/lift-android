package com.gradation.lift.feature.work.routineSelection.data.model

import com.gradation.lift.model.model.routine.Label

/**
 * @param labelSet 필터링 된 라벨 집합
 * @since 2023-11-26 18:25:18
 */
data class LabelFilterType(val labelSet: Set<Label>) {

    fun getIdList(): List<Int> =
        this.labelSet.sortedBy { it.id }.map { it.id }

    fun contains(label: Label): Boolean = labelSet.contains(label)

    fun isCheckedAllLabel(): Boolean = labelSet.size == Label.entries.size
}