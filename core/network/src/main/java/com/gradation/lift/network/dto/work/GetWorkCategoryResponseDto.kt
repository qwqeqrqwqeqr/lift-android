package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetWorkCategoryResponseDto(
    @Json(name = "work_category")
    val workCategory: List<WorkCategoryDto>
) {
    fun toDomain(): List<WorkCategory> =
        this.workCategory.map {
            WorkCategory(
                id = it.id,
                name = it.name,
                workPart = WorkPart(
                    id = it.workPart.id,
                    name = it.workPart.name
                ),
                introduce = it.introduce,
                description = it.description
            )
        }.filter { true }
}
