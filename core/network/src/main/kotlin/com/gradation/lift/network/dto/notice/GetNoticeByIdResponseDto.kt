package com.gradation.lift.network.dto.notice

import com.gradation.lift.model.model.notification.Notice
import kotlinx.serialization.SerialName

import kotlinx.datetime.LocalDate.Companion.parse
import kotlinx.serialization.Serializable

@Serializable
data class GetNoticeByIdResponseDto(
    @SerialName("notice")
    val notice: NoticeDto,
) {
    fun toDomain(): Notice =
        Notice(
            id = notice.id,
            title = notice.title,
            description = notice.description,
            category = notice.category,
            date = parse(notice.date),
        )

}
