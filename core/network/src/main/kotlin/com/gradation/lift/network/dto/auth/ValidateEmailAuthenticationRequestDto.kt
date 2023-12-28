package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ValidateEmailAuthenticationRequestDto(
    @Json(name = "email")
    val email : String,
    @Json(name = "code")
    val code : Int,
)
