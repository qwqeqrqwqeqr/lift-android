package com.gradation.lift.network.dto.notification

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNoticeResponseDto(
    @Json(name = "notice")
    val notice: List<NoticeDto>
)
