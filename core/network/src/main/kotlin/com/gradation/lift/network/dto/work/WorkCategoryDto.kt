package com.gradation.lift.network.dto.work

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkCategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("equipment")
    val equipment: String,
    @SerialName("search_tag")
    val searchTag: List<String>,
    @SerialName("work_part")
    val workPart: List<String>,
    @SerialName("introduce")
    val introduce: String,
    @SerialName("description")
    val description: String,
    @SerialName("sequence")
    val sequence: List<SequenceContentDto>,
    @SerialName("effect")
    val effect: List<EffectContentDto>,
    @SerialName("caution")
    val caution: List<String>,
)


