package com.gradation.lift.feature.home.data.state

import com.gradation.lift.model.model.badge.Badge

sealed interface BadgeConditionState {
    data class Success(val badge: Badge) : BadgeConditionState

    object None : BadgeConditionState

}