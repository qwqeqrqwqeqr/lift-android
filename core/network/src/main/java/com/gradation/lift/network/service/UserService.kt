package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.user.*
import retrofit2.http.*

interface UserService {

    @GET("user/user-detail/")
    suspend fun getUserDetail(@Header("Authorization") accessToken: String): APIResultWrapper<GetUserDetailResponseDto>

    @POST("user/user-detail/")
    suspend fun createUserDetail(
        @Header("Authorization") accessToken: String,
        createUserDetailRequestDto: CreateUserDetailRequestDto,
    ): APIResultWrapper<CreateUserDetailResponseDto>

    @PUT("user/user-detail/")
    suspend fun updateUserDetail(
        @Header("Authorization") accessToken: String,
        updateUserDetailRequestDto: UpdateUserDetailRequestDto,
    ): APIResultWrapper<UpdateUserDetailResponseDto>

    @GET("user/exist-user-detail/")
    suspend fun existUserDetail(@Header("Authorization") accessToken: String): APIResultWrapper<ExistUserDetailResponseDto>
}