package com.gradation.lift.network.dto.picture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserProfilePictureDto(
    @Json(name = "url")
    val url :String,
)