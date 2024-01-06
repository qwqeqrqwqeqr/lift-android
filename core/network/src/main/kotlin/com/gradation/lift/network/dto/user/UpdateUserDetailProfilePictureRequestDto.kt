package com.gradation.lift.network.dto.user

import kotlinx.serialization.SerialName
SerialName(

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDetailProfilePictureRequestDto(
    @SerialName("profile_picture")
    val profilePicture: String
)
