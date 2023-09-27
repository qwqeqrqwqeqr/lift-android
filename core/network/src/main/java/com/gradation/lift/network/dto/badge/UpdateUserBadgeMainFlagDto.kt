package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UpdateUserBadgeMainFlagDto(
    @Json(name = "badge_id")
    val id: Int,
    @Json(name = "main_flag")
    val mainFlag: Boolean
)
