package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class CreateRoutineSetRoutineRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("picture")
    val picture: String,
    @SerialName("weekday")
    val weekday: String,
    @SerialName("label")
    val label: String,
    @SerialName("routine")
    val routine: List<CreateRoutineDto>
)