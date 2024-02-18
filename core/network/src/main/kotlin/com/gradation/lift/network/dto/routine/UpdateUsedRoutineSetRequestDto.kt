package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class UpdateUsedRoutineSetRequestDto(
    @SerialName("routine_set_id_list")
    val routineSetIdList: List<Int>,
    @SerialName("used_time_stamp")
    val usedTimeStamp: String,
)