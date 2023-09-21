package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserBadgeDto(
    @Json(name = "badge")
    val badge: BadgeDto,
    @Json(name = "badge_time_stamp")
    val badgeTimeStamp: String,
    @Json(name = "main_flag")
    val mainFlag: Boolean
)
