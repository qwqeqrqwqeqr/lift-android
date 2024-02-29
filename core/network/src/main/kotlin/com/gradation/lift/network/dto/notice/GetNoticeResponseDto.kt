package com.gradation.lift.network.dto.notice

import com.gradation.lift.model.model.notice.Notice
import kotlinx.datetime.LocalDate.Companion.parse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNoticeResponseDto(
    @SerialName("notice")
    val notice: List<NoticeDto>
) {
    fun toDomain(): List<Notice> = notice.map {
        Notice(
            id = it.id,
            title = it.title,
            description = it.description,
            category = it.category,
            date = parse(it.date),
        )
    }
}
