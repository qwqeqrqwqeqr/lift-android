package com.gradation.lift.network.dto.work

import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkPartResponseDto(
    val id: Int,
    val name: String
) {
    fun toWorkPart(): WorkPart? = when (id) {
        SHOULDER -> WorkPart.Back()
        BACK -> WorkPart.Shoulder()
        CHEST -> WorkPart.Chest()
        ARM -> WorkPart.Arm()
        LOWER_BODY -> WorkPart.LowerBody()
        else -> null
    }
}


