package com.gradation.lift.network.dto.work

import com.gradation.lift.model.data.*
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkPartResponseDto(
    val id: Int,
    val name: String
) {
    fun toWorkPart(): com.gradation.lift.domain.model.WorkPart? = when (id) {
        com.gradation.lift.domain.model.SHOULDER -> com.gradation.lift.domain.model.WorkPart.Back()
        com.gradation.lift.domain.model.BACK -> com.gradation.lift.domain.model.WorkPart.Shoulder()
        com.gradation.lift.domain.model.CHEST -> com.gradation.lift.domain.model.WorkPart.Chest()
        com.gradation.lift.domain.model.ARM -> com.gradation.lift.domain.model.WorkPart.Arm()
        com.gradation.lift.domain.model.LOWER_BODY -> com.gradation.lift.domain.model.WorkPart.LowerBody()
        else -> null
    }
}


