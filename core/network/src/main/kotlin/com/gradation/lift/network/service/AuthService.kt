package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * [AuthService]
 * 회원 인증 및 인가 서비스
 * @since 2023-08-28 22:33:45
 **/

interface AuthService {


    /**
     * [signUpDefault]
     * 기본 방식으로 회원가입
     * @since 2023-08-28 22:33:31
     */
    @POST("auth/sign-up/default")
    suspend fun signUpDefault(
        @Body signUpDefaultRequestDto: SignUpDefaultRequestDto,
    ): Response<APIResultWrapper<SignUpDefaultResponseDto>>


    /**
     * [signInDefault]
     * 기본 방식으로 로그인
     * @since 2023-08-28 22:33:31
     */
    @POST("auth/sign-in/default")
    suspend fun signInDefault(
        @Body signInDefaultRequestDto: SignInDefaultRequestDto,
    ): Response<APIResultWrapper<SignInDefaultResponseDto>>

    /**
     * [signInKakao]
     * 카카오 로그인
     * 카카오 로그인을 진행하고, 반환된 토큰은 서버에 저장한다.
     * @since 2023-08-28 22:33:27
     */
    @POST("auth/sign-in/kakao")
    suspend fun signInKakao(
        @Body signInKakaoRequestDto: SignInKakaoRequestDto,
    ): Response<APIResultWrapper<SignInKakaoResponseDto>>


    /**
     * [signInNaver]
     * 네이버 로그인
     * 네이버 로그인을 진행하고, 반환된 토큰은 서버에 저장한다.
     * @since 2023-08-28 22:33:21
     */
    @POST("auth/sign-in/naver")
    suspend fun signInNaver(
        @Body signInNaverRequestDto: SignInNaverRequestDto,
    ): Response<APIResultWrapper<SignInNaverResponseDto>>


}

