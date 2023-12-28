package com.gradation.lift.model.model.auth

data class EmailAuthenticationValidationInfo(
    val email: String,
    val authenticationCode: Int
)
