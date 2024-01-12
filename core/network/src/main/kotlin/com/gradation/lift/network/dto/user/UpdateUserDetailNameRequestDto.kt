package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDetailNameRequestDto(
    @SerialName("name")
    val name: String
)
