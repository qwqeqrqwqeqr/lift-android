package com.gradation.lift.model.auth

data class Token(
    val accessToken :String = "",
    val refreshToken : String = ""
)

