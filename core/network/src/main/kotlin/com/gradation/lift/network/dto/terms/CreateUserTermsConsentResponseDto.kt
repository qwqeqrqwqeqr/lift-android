package com.gradation.lift.network.dto.terms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateUserTermsConsentResponseDto(
    @Json(name = "result")
    val result: Boolean,
)