package com.gradation.lift.network.dto.work

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable


@JsonClass(generateAdapter = true)
data class WorkPartDto(
    val id: Int,
    val name: String
)


