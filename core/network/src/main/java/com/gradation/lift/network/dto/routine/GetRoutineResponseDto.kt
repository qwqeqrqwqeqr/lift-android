package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRoutineResponseDto(
    @Json(name = "routine")
    val routine: List<RoutineDto>,
) {
    fun toRoutine(): List<Routine> = this.routine.map {
        Routine(
            id = it.routineId,
            routineSetId = it.routineSetId,
            workCategory = WorkCategory(
                id = it.workCategory.id,
                name = it.workCategory.name,
                workpart = WorkPart(
                    id = it.workCategory.workpart.id,
                    name = it.workCategory.workpart.name
                ),
                shortDescription = it.workCategory.shortDescription,
                longDescription = it.workCategory.longDescription
            ),
            workSetList = it.workWeightList.zip(it.workRepetitionList).map { workSet ->
                WorkSet(
                    weight = workSet.first,
                    repetition = workSet.second
                )
            },
            maxWeight = it.maxWeight,
            minWeight = it.minWeight,
            totalWeight = it.totalWeight,
            maxRepetition = it.maxRepetition,
            minRepetition = it.minRepetition,
            totalRepetition = it.totalRepetition
        )
    }
}

