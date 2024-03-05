package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.model.routine.Routine
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
            workCategoryId = it.workCategoryId,
            workCategoryName = it.workCategoryName,
            workPart = it.workPart,
            workSetList = it.workWeightList.zip(it.workRepetitionList).map { workSet ->
                WorkSet(
                    weight = workSet.first,
                    repetition = workSet.second
                )
            }
        )
    }
}

