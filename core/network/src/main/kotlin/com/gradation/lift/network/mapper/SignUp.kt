package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.GoogleSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignUpInfo
import com.gradation.lift.model.model.auth.NaverSignUpInfo
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignUpGoogleRequestDto
import com.gradation.lift.network.dto.auth.SignUpKakaoRequestDto
import com.gradation.lift.network.dto.auth.SignUpNaverRequestDto


fun DefaultSignUpInfo.toDto(): SignUpDefaultRequestDto =
    SignUpDefaultRequestDto(
        email = this.id,
        password = this.password
    )


fun NaverSignUpInfo.toDto(): SignUpNaverRequestDto =
    SignUpNaverRequestDto(id, email)

fun KakaoSignUpInfo.toDto(): SignUpKakaoRequestDto =
    SignUpKakaoRequestDto(id, email)

fun GoogleSignUpInfo.toDto(): SignUpGoogleRequestDto =
    SignUpGoogleRequestDto(id, email)