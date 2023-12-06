package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.picture.GetRoutineSetPictureResponseDto
import com.gradation.lift.network.dto.picture.GetUserProfilePictureResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * [PictureService]
 * 외부 저장소에 존재하는 사진 정보을 불러오는 서비스
 * @since 2023-08-28 22:32:18
 */
interface PictureService {


    /**
     * [getUserProfilePicture]
     * 사용자 기본 프로필에 적용할 수 있는 사진 리스트 불러오기
     * @since 2023-08-28 22:32:13
     */
    @GET("picture/user-profile")
    suspend fun getUserProfilePicture(): Response<APIResultWrapper<GetUserProfilePictureResponseDto>>

    /**
     * [getRoutineSetPicture]
     * 루틴 세트에 적용할 수 있는 사진 리스트 불러오기
     * @since 2023-08-28 22:32:08
     */
    @GET("picture/routine-set")
    suspend fun getRoutineSetPicture(): Response<APIResultWrapper<GetRoutineSetPictureResponseDto>>

}