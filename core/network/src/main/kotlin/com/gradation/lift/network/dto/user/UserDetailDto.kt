package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class UserDetailDto(
    @SerialName("name")
    val name: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("height")
    val height: Float,
    @SerialName("weight")
    val weight: Float,
    @SerialName("profile_picture")
    val profilePicture: String,
    @SerialName("unit_of_weight")
    val unitOfWeight: String,
)
