package com.gradation.lift.network.dto.terms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)

data class CreateUserTermsConsentRequestDto(
    @Json(name = "consent") val consent: Boolean,
    @Json(name = "marketing_consent") val marketingConsent: Boolean,
)