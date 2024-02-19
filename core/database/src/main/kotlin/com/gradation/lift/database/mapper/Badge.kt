package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.badge.UserBadgeEntity
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.UserBadge


fun Badge.toEntity() = BadgeEntity(
    id, name, description, hint, url, color, backgroundColor
)

fun UserBadge.toEntity() = UserBadgeEntity(
    badge = badge.toEntity(), badgeTimeStamp = badgeTimeStamp, mainFlag = mainFlag
)