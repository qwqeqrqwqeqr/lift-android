package com.gradation.lift.feature.home.common.data

import com.gradation.lift.model.model.badge.Badge

sealed interface BadgeConditionState {
    data class Success(val badge: Badge) : BadgeConditionState

    data object None : BadgeConditionState

}