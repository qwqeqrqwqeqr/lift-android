package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkCategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("work_part")
    val workPart: List<String>,
    @SerialName("introduce")
    val introduce: String?,
    @SerialName("description")
    val description: String?,
)
