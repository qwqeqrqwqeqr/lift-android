package com.gradation.lift.network.dto.user

import com.gradation.lift.network.dto.work.WorkPartDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "gender")
    val gender: String? = null,
    @Json(name = "height")
    val height: Float? = null,
    @Json(name = "weight")
    val weight: Float? = null,
    @Json(name = "profile")
    val profile: String? = null,
    @Json(name = "unit_of_weight")
    val unitOfWeight: String? = null,
)
