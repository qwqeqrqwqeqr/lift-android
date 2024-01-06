package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class ValidateEmailAuthenticationRequestDto(
    @SerialName("email")
    val email : String,
    @SerialName("code")
    val code : Int,
    @SerialName("is_signed")
    val isSigned: Boolean
)
