package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.routine.Routine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineResponseDto (
    @SerialName("routine")
    val routine: List<RoutineDetailDto>
){

}

