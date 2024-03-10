package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.model.model.badge.Badge


fun Badge.toEntity() = BadgeEntity(
    id, name, description, hint, url, color, backgroundColor
)

