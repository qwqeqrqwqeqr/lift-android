package com.gradation.lift.network.dto.user

import com.gradation.lift.network.dto.work.WorkPartDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto(
    val name: String? = null,
    val gender: String? = null,
    val height: Float? = null,
    val weight: Float? = null,
    @Json(name = "unit_of_weight")
    val unitOfWeight: String? = null,
)
