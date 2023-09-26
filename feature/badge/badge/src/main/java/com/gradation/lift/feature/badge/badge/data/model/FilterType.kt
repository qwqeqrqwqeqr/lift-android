package com.gradation.lift.feature.badge.badge.data.model


sealed interface FilterType {
    object All : FilterType
    object Acquired : FilterType
    object UnAcquired : FilterType
}
