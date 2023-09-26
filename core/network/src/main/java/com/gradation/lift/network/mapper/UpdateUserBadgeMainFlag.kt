package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagRequestDto


fun UpdateUserBadgeMainFlag.toDto(): UpdateUserBadgeMainFlagRequestDto =
    UpdateUserBadgeMainFlagRequestDto(id, mainFlag)
