package com.gradation.lift.network.dto.inquiry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateInquiryResponseDto(
    @SerialName("result") val result: Boolean,
)