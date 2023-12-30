package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateEmailAuthenticationCodeRequestDto(
    @Json(name = "email")
    val email : String,
    @Json(name = "is_signed")
    val isSigned: Boolean
)
