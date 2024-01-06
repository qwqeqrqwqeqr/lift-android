package com.gradation.lift.network.dto.badge

import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class CreateUserBadgeResponseDto(
    @SerialName("result")
    val result: Boolean
)
