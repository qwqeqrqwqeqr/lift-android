package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectContentDto(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
)