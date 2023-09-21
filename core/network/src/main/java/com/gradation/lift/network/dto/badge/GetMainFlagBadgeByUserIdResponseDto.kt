package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json

data class GetMainFlagBadgeByUserIdResponseDto(
    @Json(name = "user_badge")
    val userBadge: List<UserBadgeDto>
)
