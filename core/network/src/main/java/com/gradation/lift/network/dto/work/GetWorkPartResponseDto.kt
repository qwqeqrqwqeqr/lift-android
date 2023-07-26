package com.gradation.lift.network.dto.work

import com.gradation.lift.model.work.WorkPart
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetWorkPartResponseDto(
    @Json(name="work_part")
    val workPart: List<WorkPartDto>
) {
    fun toWorkPart(): List<WorkPart> = this.workPart.map {
        WorkPart(
            id = it.id,
            name = it.name
        )
    }
}


