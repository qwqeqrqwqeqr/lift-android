package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignInKakaoRequestDto

fun DefaultSignInInfo.toDto() : SignInDefaultRequestDto =
    SignInDefaultRequestDto(
        id = this.id,
        password = this.password
    )

fun KakaoSignInInfo.toDto() : SignInKakaoRequestDto =
    SignInKakaoRequestDto(
        id = this.id,
    )