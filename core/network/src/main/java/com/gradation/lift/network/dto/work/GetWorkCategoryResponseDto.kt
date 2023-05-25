package com.gradation.lift.network.dto.work

import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryResponseDto(
    val id: Int,
    val name: String,
    val workpart: Int,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String
) {
    fun toWorkCategory(): WorkCategory =
        WorkCategory(
            id = id,
            name = name,
            workpart = when (workpart) {
                SHOULDER -> WorkPart.Back()
                BACK -> WorkPart.Shoulder()
                CHEST -> WorkPart.Chest()
                ARM -> WorkPart.Arm()
                LOWER_BODY -> WorkPart.LowerBody()
                else -> null
            },
            shortDescription = shortDescription,
            longDescription = longDescription
        )
}
