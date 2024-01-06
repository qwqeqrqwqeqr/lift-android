package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class SignUpDefaultRequestDto(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
)
