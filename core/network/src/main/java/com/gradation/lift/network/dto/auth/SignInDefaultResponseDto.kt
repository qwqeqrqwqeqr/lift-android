package com.gradation.lift.network.dto.auth

import com.gradation.lift.model.auth.Token
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInDefaultResponseDto(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "refresh_token")
    val refreshToken: String,
) {
    fun toToken(): Token = Token(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
