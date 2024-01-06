package com.gradation.lift.network.dto.auth

import com.gradation.lift.model.model.auth.Token
import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class SignInKakaoResponseDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
){
    fun toDomain(): Token = Token(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
