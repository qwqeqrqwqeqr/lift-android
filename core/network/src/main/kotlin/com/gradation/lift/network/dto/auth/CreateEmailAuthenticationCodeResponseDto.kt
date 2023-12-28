package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateEmailAuthenticationCodeResponseDto(
    @Json(name = "result")
    val result: Boolean,
)