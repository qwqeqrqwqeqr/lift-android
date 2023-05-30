package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class CreateRoutineSetRequestDto(
    @SerialName("user_id")
    val userId: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String,
    @SerialName("repeat_type")
    val repeatType: String,
    @SerialName("repeat_interval")
    val repeatInterval: Int,
    @SerialName("routine")
    val routine: List<CreateRoutineDto>
)