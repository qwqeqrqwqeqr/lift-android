package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.user.*
import retrofit2.Response
import retrofit2.http.*

/**
 * [UserService]
 * 사용자 정보 서비스
 */
interface UserService {

    /**
     * [getUserDetail]
     * 사용자 상세정보 불러오기
     */
    @GET("user/user-detail/")
    suspend fun getUserDetail(): Response<APIResultWrapper<GetUserDetailResponseDto>>


    /**
     * [createUserDetail]
     * 사용자 상세정보 생성하기
     */
    @POST("user/user-detail/")
    suspend fun createUserDetail(
        @Body createUserDetailRequestDto: CreateUserDetailRequestDto
    ): Response<APIResultWrapper<CreateUserDetailResponseDto>>

    /**
     * [createUserDetail]
     * 사용자 상세정보 업데이트하기
     */
    @PUT("user/user-detail/")
    suspend fun updateUserDetail(@Body updateUserDetailRequestDto: UpdateUserDetailRequestDto): Response<APIResultWrapper<UpdateUserDetailResponseDto>>

    /**
     * [createUserDetail]
     * 사용자의 상세정보가 존재하는지 확인하기
     */
    @GET("user/exist-user-detail/")
    suspend fun existUserDetail(): Response<APIResultWrapper<ExistUserDetailResponseDto>>


}