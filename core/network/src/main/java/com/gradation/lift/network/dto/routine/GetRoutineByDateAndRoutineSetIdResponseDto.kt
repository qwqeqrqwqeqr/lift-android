package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName

data class GetRoutineByDateAndRoutineSetIdResponseDto(
    @SerialName("routine")
    val routine: List<RoutineDetailDto>
)
