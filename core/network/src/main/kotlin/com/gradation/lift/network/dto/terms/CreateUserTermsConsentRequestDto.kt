package com.gradation.lift.network.dto.terms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserTermsConsentRequestDto(
    @SerialName("consent") val consent: Boolean,
    @SerialName("marketing_consent") val marketingConsent: Boolean,
)