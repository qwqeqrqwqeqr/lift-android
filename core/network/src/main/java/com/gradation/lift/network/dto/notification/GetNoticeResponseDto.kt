package com.gradation.lift.network.dto.notification

import com.gradation.lift.model.model.notification.Notice
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate.Companion.parse

@JsonClass(generateAdapter = true)
data class GetNoticeResponseDto(
    @Json(name = "notice")
    val notice: List<NoticeDto>
) {
    fun toDomain(): List<Notice> = notice.map {
        Notice(
            id = it.id,
            title = it.title,
            description = it.description,
            date = parse(it.date),
        )
    }
}
