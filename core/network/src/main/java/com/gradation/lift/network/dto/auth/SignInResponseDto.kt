package com.gradation.lift.network.dto.auth

import com.gradation.lift.network.dto.routine.RoutineSetDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInResponseDto(
    @Json(name = "access_token")
    val accessToken : String,
    @Json(name = "refresh_token")
    val refreshToken : String,
)
