package com.gradation.lift.network.dto.auth

import com.gradation.lift.model.auth.Token
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpDefaultResponseDto(
    @Json(name = "result")
    val result: Boolean,

)