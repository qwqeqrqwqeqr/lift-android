package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.userBadge.UserBadgeEntity
import com.gradation.lift.model.model.badge.UserBadge


fun UserBadge.toEntity() = UserBadgeEntity(
    id = badge.id,
    name = badge.name,
    description = badge.description,
    hint = badge.hint,
    url = badge.url,
    color = badge.color,
    backgroundColor = badge.backgroundColor,
    badgeTimeStamp = badgeTimeStamp,
    mainFlag = mainFlag
)