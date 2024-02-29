package com.gradation.lift.network.dto.picture


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserProfilePictureResponseDto(
    @SerialName("user_profile_picture")
    val userProfilePicture : List<UserProfilePictureDto>
){
    fun toDomain() = this.userProfilePicture.map {
       it.toDomain()
    }
}
