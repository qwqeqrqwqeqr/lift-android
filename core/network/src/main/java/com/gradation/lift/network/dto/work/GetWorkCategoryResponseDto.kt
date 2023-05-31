package com.gradation.lift.network.dto.work

import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryResponseDto(
    @SerialName("work_category")
    val workCategory: List<WorkCategoryDto>
) {
    fun toWorkCategory(): List<WorkCategory> =
        this.workCategory.map {
            WorkCategory(
                id = it.id,
                name = it.name,
                workpart = WorkPart(
                    id= it.workpart.id,
                    name =it.workpart.name
                ),
                shortDescription = it.shortDescription,
                longDescription = it.longDescription
            )
        }.filter { it.workpart!=null }
}
