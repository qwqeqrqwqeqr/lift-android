package com.gradation.lift.network.dto.checker

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class CheckDuplicateNameResponseDto(
    @SerialName("result")
    val result: Boolean,

)
