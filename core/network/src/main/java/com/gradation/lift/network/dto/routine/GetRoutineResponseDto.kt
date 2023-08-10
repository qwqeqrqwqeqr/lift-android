package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.model.work.WorkSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRoutineResponseDto(
    @Json(name = "routine")
    val routine: List<RoutineDto>,
) {
    fun toDomain(): List<Routine> = this.routine.map {
        Routine(
            id = it.routineId,
            routineSetId = it.routineSetId,
            workCategory = WorkCategory(
                id = it.workCategory.id,
                name = it.workCategory.name,
                workPart = WorkPart(
                    id = it.workCategory.workPart.id,
                    name = it.workCategory.workPart.name
                ),
                introduce = it.workCategory.introduce,
                description = it.workCategory.description
            ),
            workSetList = it.workWeightList.zip(it.workRepetitionList).map { workSet ->
                WorkSet(
                    weight = workSet.first,
                    repetition = workSet.second
                )
            }
        )
    }
}

