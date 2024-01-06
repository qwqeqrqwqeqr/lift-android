package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRoutineSetRoutineResponseDto(
    @SerialName("result")
    val result: Boolean,
)