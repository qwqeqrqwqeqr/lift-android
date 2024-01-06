package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class ExistUserDetailResponseDto(
    @SerialName("result")
    val result : Boolean
)
