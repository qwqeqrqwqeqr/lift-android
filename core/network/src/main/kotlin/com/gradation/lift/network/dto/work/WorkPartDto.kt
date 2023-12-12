package com.gradation.lift.network.dto.work

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WorkPartDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)


