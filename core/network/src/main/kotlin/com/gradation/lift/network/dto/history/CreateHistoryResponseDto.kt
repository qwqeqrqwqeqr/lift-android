package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class CreateHistoryResponseDto(
    @SerialName("result")
    val result: Boolean,
)