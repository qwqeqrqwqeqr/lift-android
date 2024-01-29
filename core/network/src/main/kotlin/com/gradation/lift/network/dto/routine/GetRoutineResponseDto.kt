package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkSet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineResponseDto(
    @SerialName("routine")
    val routine: List<RoutineDto>,
) {
    fun toDomain(): List<Routine> = this.routine.map {
        Routine(
            id = it.routineId,
            routineSetId = it.routineSetId,
            workCategory = WorkCategory(
                id = it.workCategory.id,
                name = it.workCategory.name,
                workPart = it.workCategory.workPart,
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

