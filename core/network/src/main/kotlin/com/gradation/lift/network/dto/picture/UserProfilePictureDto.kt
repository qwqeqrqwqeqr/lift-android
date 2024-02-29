package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.Constants
import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class UserProfilePictureDto(
    @SerialName("id")
    val id : Int,
    @SerialName("url")
    val url :String,
) {
    fun toDomain() = UserProfilePicture(
        id, Constants.DEFAULT_S3_URL + url
    )
}