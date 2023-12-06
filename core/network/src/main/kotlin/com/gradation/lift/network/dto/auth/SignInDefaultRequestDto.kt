package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInDefaultRequestDto(
    @Json(name = "id")
    val id : String,
    @Json(name = "password")
    val password : String,
)