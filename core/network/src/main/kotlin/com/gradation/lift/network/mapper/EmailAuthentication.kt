package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import com.gradation.lift.network.dto.auth.CreateEmailAuthenticationCodeRequestDto
import com.gradation.lift.network.dto.auth.ValidateEmailAuthenticationRequestDto

fun EmailAuthenticationInfo.toDto(): CreateEmailAuthenticationCodeRequestDto =
    CreateEmailAuthenticationCodeRequestDto(email,isSigned)


fun EmailAuthenticationValidationInfo.toDto(): ValidateEmailAuthenticationRequestDto =
    ValidateEmailAuthenticationRequestDto(email, authenticationCode,isSigned)
