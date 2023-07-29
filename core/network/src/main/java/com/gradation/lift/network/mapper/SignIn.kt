package com.gradation.lift.network.mapper

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto

fun SignInInfo.toDto() : SignInDefaultRequestDto =
    SignInDefaultRequestDto(
        id = this.id,
        password = this.password
    )