package com.gradation.lift.network.dto.routine

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@JsonClass(generateAdapter = true)
data class RoutineSetDto(
    val id: Int,
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "long_description")
    val longDescription: String,
    @Json(name = "repeat_type")
    val repeatType: String,
    @Json(name = "repeat_interval")
    val repeatInterval: Int
)
