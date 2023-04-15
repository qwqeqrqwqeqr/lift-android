package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryResponseDto(
    val id: Int,
    val name: String,
    val workpart: Int,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String
)