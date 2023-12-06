package com.gradation.lift.network.dto.badge

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUserBadgeRequestDto(
    @Json(name = "user_badge")
    val createUserBadgeDto: CreateUserBadgeDto
)
