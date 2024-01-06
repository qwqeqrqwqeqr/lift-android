package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RefreshResponseDto(
    @SerialName("access_token")
    val accessToken : String
)

