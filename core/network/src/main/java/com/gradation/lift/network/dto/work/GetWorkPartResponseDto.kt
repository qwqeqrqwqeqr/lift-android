package com.gradation.lift.network.dto.work

import com.gradation.lift.model.work.WorkPart
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetWorkPartResponseDto(
    val workpart: List<WorkPartDto>
) {
    fun toWorkPart(): List<WorkPart> = this.workpart.map {
        WorkPart(
            id = it.id,
            name = it.name
        )
    }
}


