package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetWorkCategoryByIdResponseDto(
    @Json(name = "work_category")
    val workCategory: WorkCategoryDto
) {
    fun toDomain(): WorkCategory =
        WorkCategory(
            id = workCategory.id,
            name = workCategory.name,
            workPart = WorkPart(
                id = workCategory.workPart.id,
                name = workCategory.workPart.name
            ),
            introduce = workCategory.introduce,
            description = workCategory.description
        )
}
