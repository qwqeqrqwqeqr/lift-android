package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.network.common.Constants
import kotlinx.serialization.SerialName


import kotlinx.serialization.Serializable

@Serializable
data class RoutineSetPictureDto(
    @SerialName("id")
    val id: Int,
    @SerialName("category")
    val category: String,
    @SerialName("url")
    val url: String,
) {
    fun toDomain() = RoutineSetPicture(
        id, category, Constants.DEFAULT_S3_URL + url
    )
}
