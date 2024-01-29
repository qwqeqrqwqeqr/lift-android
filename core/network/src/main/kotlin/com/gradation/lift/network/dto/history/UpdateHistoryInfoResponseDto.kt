package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class UpdateHistoryInfoResponseDto(
    @SerialName("result")
    val result: Boolean,
)