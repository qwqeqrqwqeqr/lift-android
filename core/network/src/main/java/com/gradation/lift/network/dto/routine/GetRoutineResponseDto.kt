package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.routine.Routine
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineResponseDto (
    val id: Int,
    val routine_set_id: Int,
    val routine: RoutineDto,
    val max_weight: Int,
    val min_weight: Int,
    val total_weight: Int,
    val max_repetition: Int,
    val min_repetition: Int,
    val total_repetition: Int,
){
    fun toRoutine(): Routine =
        Routine(
            id=id,
            routine_set_id=routine_set_id,
            workCategory = routine.work_category,
            workWeightList = routine.work_weight_list,
            workRepetitionList = routine.work_repetition_list,
            max_weight=max_weight,
            min_weight=min_weight,
            total_weight=total_weight,
            max_repetition=max_repetition,
            min_repetition=min_repetition,
            total_repetition=total_repetition
        )

}

//TODO Workcategory 다른형태로 받도록 수정할 것