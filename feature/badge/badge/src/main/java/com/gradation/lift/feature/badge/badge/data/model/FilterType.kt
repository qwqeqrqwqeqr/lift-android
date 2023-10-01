package com.gradation.lift.feature.badge.badge.data.model


sealed interface FilterType {
    data class All(val title: String = "전체") : FilterType
    data class Acquired(val title: String = "획득 뱃지") : FilterType
    data class UnAcquired(val title: String = "미획득 뱃지") : FilterType

    fun getTitleName(): String =
        when (this) {
            is Acquired -> this.title
            is All -> this.title
            is UnAcquired -> this.title
        }
}
