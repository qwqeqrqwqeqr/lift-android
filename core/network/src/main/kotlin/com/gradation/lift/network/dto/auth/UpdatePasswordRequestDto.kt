package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UpdatePasswordRequestDto(
    @Json(name = "email")
    val email : String,
    @Json(name = "password")
    val password : String,
)
