package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkCategoryByWorkPartResponseDto (
    val id: Int,
    val name: String,
    val workpart: Int,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String
){
    fun toWorkCategory(): com.gradation.lift.domain.model.WorkCategory =
        com.gradation.lift.domain.model.WorkCategory(
            id = id,
            name = name,
            workpart = when (workpart) {
                com.gradation.lift.domain.model.SHOULDER -> com.gradation.lift.domain.model.WorkPart.Back()
                com.gradation.lift.domain.model.BACK -> com.gradation.lift.domain.model.WorkPart.Shoulder()
                com.gradation.lift.domain.model.CHEST -> com.gradation.lift.domain.model.WorkPart.Chest()
                com.gradation.lift.domain.model.ARM -> com.gradation.lift.domain.model.WorkPart.Arm()
                com.gradation.lift.domain.model.LOWER_BODY -> com.gradation.lift.domain.model.WorkPart.LowerBody()
                else -> null
            },
            shortDescription = shortDescription,
            longDescription = longDescription
        )
}