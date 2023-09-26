package com.gradation.lift.feature.badge.badge.data.model

sealed interface SortType {
    object Number : SortType
    object Name : SortType
    object Oldest : SortType
    object Newest : SortType
}
