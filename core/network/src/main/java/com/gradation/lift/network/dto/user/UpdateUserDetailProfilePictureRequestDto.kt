package com.gradation.lift.network.dto.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserDetailProfilePictureRequestDto(
    @Json(name = "profile_picture")
    val profilePicture: String
)
