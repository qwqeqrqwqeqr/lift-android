package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.network.dto.badge.CreateUserBadgeDto


fun CreateUserBadge.toDto(): CreateUserBadgeDto =
    CreateUserBadgeDto(
        id = id,
        badgeTimeStamp = badgeTimeStamp.toString()
    )


