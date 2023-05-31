package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineResponseDto (
    @SerialName("routine")
    val routine: List<RoutineDetailDto>
){
    fun toRoutine(): List<Routine> = this.routine.map {
        Routine(
            id = it.id,
            routineSetId = it.routineSetId,
            workCategory = WorkCategory(
                id = it.workCategory.id,
                name = it.workCategory.name,
                workpart = WorkPart(
                    id= it.workCategory.workpart.id,
                    name =it.workCategory.workpart.name
                ),
            ),
            workSetList = it.workWeightList.zip(it.workRepetitionList).map { workSet ->
                WorkSet(weight = workSet.first, repetition = workSet.second)
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

