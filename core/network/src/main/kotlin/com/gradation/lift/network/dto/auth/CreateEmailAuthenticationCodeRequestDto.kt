package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateEmailAuthenticationCodeRequestDto(
    @SerialName("email")
    val email : String,
    @SerialName("is_signed")
    val isSigned: Boolean
)
