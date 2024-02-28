package com.gradation.lift.network.dto.inquiry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateInquiryRequestDto(
    @SerialName("content") val content: String,
)