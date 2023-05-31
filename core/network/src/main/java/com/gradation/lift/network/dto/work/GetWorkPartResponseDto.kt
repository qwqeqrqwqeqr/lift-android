package com.gradation.lift.network.dto.work

import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.Serializable


@Serializable
data class GetWorkPartResponseDto(
    val workpart: List<WorkPartDto>
) {
    fun toWorkPart(): List<WorkPart> = this.workpart.map {
        WorkPart(
            id= it.id,
            name =it.name
        )
    }
}


