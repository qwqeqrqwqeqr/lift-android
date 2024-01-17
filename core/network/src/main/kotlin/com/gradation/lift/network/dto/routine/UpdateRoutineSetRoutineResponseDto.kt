package com.gradation.lift.network.dto.routine

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class UpdateRoutineSetRoutineResponseDto(
    @SerialName("result")
    val result: Boolean,
)