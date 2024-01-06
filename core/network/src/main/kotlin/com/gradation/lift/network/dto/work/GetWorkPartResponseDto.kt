package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.WorkPart
import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class GetWorkPartResponseDto(
    @SerialName("work_part")
    val workPart: List<WorkPartDto>
) {
    fun toDomain(): List<WorkPart> = this.workPart.map {
        WorkPart(
            id = it.id,
            name = it.name
        )
    }
}


