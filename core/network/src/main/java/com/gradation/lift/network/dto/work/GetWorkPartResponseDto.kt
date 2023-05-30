package com.gradation.lift.network.dto.work

import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkPartResponseDto(
    val workpart: List<WorkPartDto>
) {
    fun toWorkPart(): List<WorkPart> = this.workpart.mapNotNull {
        when (it.id) {
            SHOULDER -> WorkPart.Back()
            BACK -> WorkPart.Shoulder()
            CHEST -> WorkPart.Chest()
            ARM -> WorkPart.Arm()
            LOWER_BODY -> WorkPart.LowerBody()
            else -> null
        }
    }
}


