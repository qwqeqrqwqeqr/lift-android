package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagRequestDto


fun UpdateUserBadgeMainFlag.toDto(): UpdateUserBadgeMainFlagRequestDto =
    UpdateUserBadgeMainFlagRequestDto(this.updateUserBadgeMainFlag.map {
        UpdateUserBadgeMainFlagDto(it.first, it.second)
    })
