package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryResponseDto(
    @SerialName("work_category")
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
        }
}
