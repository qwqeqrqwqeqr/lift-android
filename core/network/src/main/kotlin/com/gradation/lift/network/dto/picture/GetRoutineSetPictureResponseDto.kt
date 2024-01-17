package com.gradation.lift.network.dto.picture

import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.network.common.Constants
import kotlinx.serialization.SerialName



import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetPictureResponseDto(
    @SerialName("routine_set_picture")
    val getRoutineSetPicture : List<RoutineSetPictureDto>
){
    fun toDomain() :List<RoutineSetPicture> = this.getRoutineSetPicture.map {
        RoutineSetPicture(
            id = it.id,
            url = Constants.DEFAULT_S3_URL+it.url,
            category = it.category
        )
    }
}
