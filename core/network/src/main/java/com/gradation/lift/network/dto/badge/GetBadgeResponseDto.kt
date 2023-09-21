package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetBadgeResponseDto(
    @Json(name = "badge")
    val badge: List<BadgeDto>
)
