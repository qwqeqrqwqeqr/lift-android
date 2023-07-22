package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.picture.UserProfilePicture
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetUserProfilePictureResponseDto(
    @Json(name = "user_profile_picture")
    val userProfilePicture : List<UserProfilePictureDto>
){
    fun toUserProfilePicture() = this.userProfilePicture.map {
        UserProfilePicture(
            url = it.url
        )
    }
}
