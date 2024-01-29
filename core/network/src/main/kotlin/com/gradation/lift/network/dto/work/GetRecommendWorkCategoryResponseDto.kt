package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRecommendWorkCategoryResponseDto(
    @SerialName("work_category")
    val workCategory: List<WorkCategoryDto>
) {
    fun toDomain(): List<WorkCategory> =
        this.workCategory.map {
            WorkCategory(
                id = it.id,
                name = it.name,
                workPart = it.workPart,
                introduce = it.introduce,
                description = it.description
            )
        }
}
