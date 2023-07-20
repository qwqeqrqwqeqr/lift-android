package com.gradation.lift.network.dto.work

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WorkCategoryDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "workpart")
    val workpart: WorkPartDto,
    @Json(name = "introduce")
    val introduce: String,
    @Json(name = "description")
    val description: String
)
