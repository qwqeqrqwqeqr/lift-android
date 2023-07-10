package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.user.*
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user/user-detail/")
    suspend fun getUserDetail(): Response<APIResultWrapper<GetUserDetailResponseDto>>

    @POST("user/user-detail/")
    suspend fun createUserDetail(
        @Body createUserDetailRequestDto: CreateUserDetailRequestDto
    ): Response<APIResultWrapper<CreateUserDetailResponseDto>>

    @PUT("user/user-detail/")
    suspend fun updateUserDetail(@Body updateUserDetailRequestDto: UpdateUserDetailRequestDto): Response<APIResultWrapper<UpdateUserDetailResponseDto>>

    @GET("user/exist-user-detail/")
    suspend fun existUserDetail(): Response<APIResultWrapper<ExistUserDetailResponseDto>>


}