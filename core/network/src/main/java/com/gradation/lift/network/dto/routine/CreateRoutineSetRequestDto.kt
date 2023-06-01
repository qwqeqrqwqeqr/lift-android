package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@JsonClass(generateAdapter = true)
data class CreateRoutineSetRequestDto(
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "long_description")
    val longDescription: String,
    @Json(name = "repeat_type")
    val repeatType: String,
    @Json(name = "repeat_interval")
    val repeatInterval: Int,
    @Json(name = "routine")
    val routine: List<CreateRoutineDto>
)