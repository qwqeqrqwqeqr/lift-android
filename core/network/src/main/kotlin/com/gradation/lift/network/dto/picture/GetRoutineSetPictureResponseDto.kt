package com.gradation.lift.network.dto.picture


import com.gradation.lift.model.model.picture.RoutineSetPicture
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetPictureResponseDto(
    @SerialName("routine_set_picture")
    val getRoutineSetPicture : List<RoutineSetPictureDto>
){
    fun toDomain() :List<RoutineSetPicture> = this.getRoutineSetPicture.map {
       it.toDomain()
    }
}
