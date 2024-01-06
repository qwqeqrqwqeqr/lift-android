package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class SignUpKakaoRequestDto(
    @SerialName("id")
    val id : String,
    @SerialName("email")
    val email : String,
)
