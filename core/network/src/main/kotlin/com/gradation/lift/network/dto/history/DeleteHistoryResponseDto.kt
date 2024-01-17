package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class DeleteHistoryResponseDto(
    @SerialName("result")
    val result: Boolean,
)
