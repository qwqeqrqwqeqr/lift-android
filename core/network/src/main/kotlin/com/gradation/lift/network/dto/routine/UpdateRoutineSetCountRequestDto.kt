package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class UpdateRoutineSetCountRequestDto(
    @SerialName("routine_set_id_list")
    val routineSetIdList: List<Int>,
)