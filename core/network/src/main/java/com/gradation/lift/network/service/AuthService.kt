package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * [AuthService]
 * 회원 인증 및 인가 서비스
 **/

interface AuthService {


    /**
     * [signUpDefault]
     * 기본 방식으로 회원가입
     */
    @POST("auth/sign-up/default/")
    suspend fun signUpDefault(
        @Body signUpDefaultRequestDto: SignUpDefaultRequestDto,
    ): Response<APIResultWrapper<SignUpDefaultResponseDto>>


    /**
     * [signInDefault]
     * 기본 방식으로 로그인
     */
    @POST("auth/sign-in/default/")
    suspend fun signInDefault(
        @Body signInDefaultRequestDto: SignInDefaultRequestDto,
    ): Response<APIResultWrapper<SignInDefaultResponseDto>>


}

