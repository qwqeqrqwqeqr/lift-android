package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUserBadgeDto(
    @Json(name = "badge_id")
    val id: Int,
    @Json(name = "badge_time_stamp")
    val badgeTimeStamp: String
)
