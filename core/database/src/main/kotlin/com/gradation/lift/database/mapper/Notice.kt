package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.notice.NoticeEntity
import com.gradation.lift.model.model.notification.Notice


fun Notice.toEntity() = NoticeEntity(
    id, title, description,category, date
)

