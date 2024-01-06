package com.gradation.lift.network.dto.picture

import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class RoutineSetPictureDto(
    @SerialName("id")
    val id : Int,
    @SerialName("category")
    val category :String,
    @SerialName("url")
    val url :String,
)
