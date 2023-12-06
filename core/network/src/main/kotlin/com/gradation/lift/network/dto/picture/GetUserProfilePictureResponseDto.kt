package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.network.common.Constants.DEFAULT_S3_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetUserProfilePictureResponseDto(
    @Json(name = "user_profile_picture")
    val userProfilePicture : List<UserProfilePictureDto>
){
    fun toDomain() = this.userProfilePicture.map {
        UserProfilePicture(
            id = it.id,
            url = DEFAULT_S3_URL+it.url
        )
    }
}