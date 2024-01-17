package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDetailInfoRequestDto(
    @SerialName("gender")
    val gender: String,
    @SerialName("height")
    val height: Float,
    @SerialName("weight")
    val weight: Float,
    @SerialName("unit_of_weight")
    val unitOfWeight: String,
)
