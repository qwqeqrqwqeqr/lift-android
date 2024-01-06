package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordRequestDto(
    @SerialName("email")
    val email : String,
    @SerialName("password")
    val password : String,
)
