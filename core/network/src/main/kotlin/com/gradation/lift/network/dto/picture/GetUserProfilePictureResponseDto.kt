package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.Constants.DEFAULT_S3_URL
import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class GetUserProfilePictureResponseDto(
    @SerialName("user_profile_picture")
    val userProfilePicture : List<UserProfilePictureDto>
){
    fun toDomain() = this.userProfilePicture.map {
        UserProfilePicture(
            id = it.id,
            url = DEFAULT_S3_URL+it.url
        )
    }
}
