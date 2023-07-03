package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpDefaultRequestDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "phone_number")
    val phoneNumber: String? = null,
)
