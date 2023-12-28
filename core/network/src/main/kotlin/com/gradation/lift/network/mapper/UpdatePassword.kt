package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import com.gradation.lift.network.dto.auth.UpdatePasswordRequestDto

fun UpdatePasswordInfo.toDto(): UpdatePasswordRequestDto =
    UpdatePasswordRequestDto(email, password)
