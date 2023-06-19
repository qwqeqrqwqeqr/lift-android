package com.gradation.lift.network.dto.work

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WorkCategoryDto(
    val id: Int,
    val name: String,
    val workpart: WorkPartDto,
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "long_description")
    val longDescription: String
)
