package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class SignUpKakaoResponseDto(
    @SerialName("result")
    val result: Boolean,
)