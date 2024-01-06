package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class UserBadgeDto(
    @SerialName("badge")
    val badge: BadgeDto,
    @SerialName("badge_time_stamp")
    val badgeTimeStamp: String,
    @SerialName("main_flag")
    val mainFlag: Boolean
)
