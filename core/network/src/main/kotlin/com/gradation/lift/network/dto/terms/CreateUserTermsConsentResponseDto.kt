package com.gradation.lift.network.dto.terms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserTermsConsentResponseDto(
    @SerialName("result")
    val result: Boolean,
)