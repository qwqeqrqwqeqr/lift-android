package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto

fun DefaultSignInInfo.toDto() : SignInDefaultRequestDto =
    SignInDefaultRequestDto(
        id = this.id,
        password = this.password
    )