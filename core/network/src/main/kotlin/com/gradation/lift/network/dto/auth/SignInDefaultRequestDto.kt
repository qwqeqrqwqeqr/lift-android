package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(
import kotlinx.serialization.Serializable


@Serializable
data class SignInDefaultRequestDto(
    @SerialName("id")
    val id : String,
    @SerialName("password")
    val password : String,
)
