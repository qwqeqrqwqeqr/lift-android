package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("auth/sign-up/default/")
    suspend fun signUpDefault(@Body signUpDefaultRequestDto: SignUpDefaultRequestDto,
    ): Response<APIResultWrapper<SignUpDefaultResponseDto>>


    @POST("auth/sign-in/default/")
    suspend fun signInDefault(@Body signInDefaultRequestDto: SignInDefaultRequestDto,
    ): Response<APIResultWrapper<SignInDefaultResponseDto>>




}

