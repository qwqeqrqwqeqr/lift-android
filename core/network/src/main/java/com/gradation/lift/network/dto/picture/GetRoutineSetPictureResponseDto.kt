package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.network.common.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetRoutineSetPictureResponseDto(
    @Json(name = "routine_set_picture")
    val getRoutineSetPicture : List<RoutineSetPictureDto>
){
    fun toRoutineSetPicture() :List<RoutineSetPicture> = this.getRoutineSetPicture.map {
        RoutineSetPicture(
            id = it.id,
            url = Constants.DEFAULT_S3_URL+it.url,
            category = it.category
        )
    }
}
