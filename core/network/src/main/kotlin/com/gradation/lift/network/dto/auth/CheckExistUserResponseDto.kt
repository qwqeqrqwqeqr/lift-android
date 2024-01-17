package com.gradation.lift.network.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CheckExistUserResponseDto(
    @SerialName("result")
    val result: Boolean,

)
