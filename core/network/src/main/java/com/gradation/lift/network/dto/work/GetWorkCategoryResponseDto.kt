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
        this.workCategory.map{
            WorkCategory(
                id = it.id,
                name = it.name,
                workpart = when (it.workpart.id) {
                    SHOULDER -> WorkPart.Back()
                    BACK -> WorkPart.Shoulder()
                    CHEST -> WorkPart.Chest()
                    ARM -> WorkPart.Arm()
                    LOWER_BODY -> WorkPart.LowerBody()
                    else -> null
                },
                shortDescription = it.shortDescription,
                longDescription = it.longDescription
            )
        }
}
