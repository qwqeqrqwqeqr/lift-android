package com.gradation.lift.network.dto.checker

import kotlinx.serialization.SerialName
SerialName(


import kotlinx.serialization.Serializable

@Serializable
data class CheckDuplicateEmailResponseDto(
    @SerialName("result")
    val result: Boolean,

)
