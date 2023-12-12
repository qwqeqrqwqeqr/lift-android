package com.gradation.lift.model.model.badge

import kotlinx.datetime.LocalDateTime

data class CreateUserBadge(
    val id: Int,
    val badgeTimeStamp: LocalDateTime
)
