package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class SignUpDefaultResponseDto(
    @SerialName("result")
    val result: Boolean,

)
