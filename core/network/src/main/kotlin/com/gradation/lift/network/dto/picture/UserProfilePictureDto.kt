package com.gradation.lift.network.dto.picture

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class UserProfilePictureDto(
    @SerialName("id")
    val id : Int,
    @SerialName("url")
    val url :String,
)
