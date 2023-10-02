package com.gradation.lift.model.model.badge

import kotlinx.datetime.LocalDateTime

data class UserBadge(
    val badge: Badge,
    val badgeTimeStamp: LocalDateTime,
    val mainFlag: Boolean
)
