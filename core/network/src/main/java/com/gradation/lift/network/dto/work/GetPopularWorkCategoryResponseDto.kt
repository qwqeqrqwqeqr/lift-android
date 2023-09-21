package com.gradation.lift.network.dto.work

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class GetPopularWorkCategoryResponseDto(
    @Json(name = "work_category")
    val workCategory: List<WorkCategoryDto>
)
