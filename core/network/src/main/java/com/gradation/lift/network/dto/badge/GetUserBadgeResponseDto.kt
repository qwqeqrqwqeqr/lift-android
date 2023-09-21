package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUserBadgeResponseDto(
    @Json(name = "user_badge")
    val userBadge: List<UserBadgeDto>
)
