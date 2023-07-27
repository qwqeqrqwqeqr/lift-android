package com.gradation.lift.network.dto.user

import com.gradation.lift.network.dto.work.WorkPartDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "height")
    val height: Float,
    @Json(name = "weight")
    val weight: Float,
    @Json(name = "profile_picture")
    val profilePicture: String,
    @Json(name = "unit_of_weight")
    val unitOfWeight: String,
)
