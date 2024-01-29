package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
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
            workPart = workCategory.workPart,
            introduce = workCategory.introduce,
            description = workCategory.description
        )
}
