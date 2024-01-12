package com.gradation.lift.network.dto.user

import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.auth.toLoginMethod
import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class GetUserAuthenticationMethodResponseDto(
    @SerialName("authentication_method")
    val authenticationMethod: String,
) {
    fun toDomain(): LoginMethod = this.authenticationMethod.toLoginMethod()
}


