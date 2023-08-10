package com.gradation.lift.model.model.auth


/**
 * [Token]
 * 토큰 정보를 담고 있는 모델
 * @param accessToken
 * @param refreshToken
 */
data class Token(
    val accessToken :String = "",
    val refreshToken : String = ""
)

