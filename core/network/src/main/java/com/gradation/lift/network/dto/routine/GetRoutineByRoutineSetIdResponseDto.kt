package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.work.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetRoutineByRoutineSetIdResponseDto(
    @SerialName("routine")
    val routine: List<RoutineDetailDto>
){
    fun toRoutine(): List<Routine> = this.routine.map {
        Routine(
            id = it.id,
            routine_set_id = it.routineSetId,
            workCategory = WorkCategory(
                id = it.workCategory.id,
                name = it.workCategory.name,
                workpart = when (it.workCategory.workpart.id) {
                    SHOULDER -> WorkPart.Back()
                    BACK -> WorkPart.Shoulder()
                    CHEST -> WorkPart.Chest()
                    ARM -> WorkPart.Arm()
                    LOWER_BODY -> WorkPart.LowerBody()
                    else -> null
                },
            ),
            workSetList = it.workWeightList.zip(it.workRepetitionList).map { workSet ->
                WorkSet(weight = workSet.first, repetition = workSet.second)
            },
            max_weight = it.maxWeight,
            min_weight = it.minWeight,
            total_weight = it.totalWeight,
            max_repetition = it.maxRepetition,
            min_repetition = it.minRepetition,
            total_repetition = it.totalRepetition
        )
    }
}
