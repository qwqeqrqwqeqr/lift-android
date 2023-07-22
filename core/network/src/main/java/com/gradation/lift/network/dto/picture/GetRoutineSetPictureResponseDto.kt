package com.gradation.lift.network.dto.picture

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetRoutineSetPictureResponseDto(
    @Json(name = "routine_set_picture")
    val getRoutineSetPicture : List<RoutineSetPictureDto>
)
