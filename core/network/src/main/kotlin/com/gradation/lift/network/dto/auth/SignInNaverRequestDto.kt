package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class SignInNaverRequestDto(
    @SerialName("id")
    val id : String,
    @SerialName("email")
    val email : String,
)
