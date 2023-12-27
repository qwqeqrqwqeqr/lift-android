package com.gradation.lift.network.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpKakaoRequestDto(
    @Json(name = "id")
    val id : String,
    @Json(name = "email")
    val email : String,
)
