package com.gradation.lift.network.dto.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateHistoryInfoRequestDto(
    @SerialName("history_id")
    val historyId: Int,
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("score")
    val score: Int? = null,
)
