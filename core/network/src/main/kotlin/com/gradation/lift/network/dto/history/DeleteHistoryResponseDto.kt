package com.gradation.lift.network.dto.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteHistoryResponseDto(
    @Json(name = "result")
    val result: Boolean,
)
