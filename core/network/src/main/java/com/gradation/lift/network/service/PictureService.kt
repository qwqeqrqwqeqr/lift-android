package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.picture.GetRoutineSetPictureResponseDto
import com.gradation.lift.network.dto.picture.GetUserProfilePictureResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface PictureService {

    @GET("picture/user-profile/")
    suspend fun getUserProfilePicture(): Response<APIResultWrapper<GetUserProfilePictureResponseDto>>

    @GET("picture/routine-set/")
    suspend fun getRoutineSetPicture(): Response<APIResultWrapper<GetRoutineSetPictureResponseDto>>

}