package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class RoutineSetDto(
    @SerialName("routine_set_id")
    val routineSetId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("weekday")
    val weekday: String,
    @SerialName("picture")
    val picture: String,
    @SerialName("label")
    val label: String,
    @SerialName("count")
    val count: Int

)


