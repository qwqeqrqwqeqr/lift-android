package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryByIdResponseDto(
    @SerialName("work_category")
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
