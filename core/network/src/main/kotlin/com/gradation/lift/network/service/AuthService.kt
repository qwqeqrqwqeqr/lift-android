package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

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
     * [signUpKakao]
     * 카카오 회원가입
     * 카카오 회원가입을 진행합니다
     * @since 2023-12-27 14:53:40
     */
    @POST("auth/sign-up/kakao")
    suspend fun signUpKakao(
        @Body signUpKakaoRequestDto: SignUpKakaoRequestDto,
    ): Response<APIResultWrapper<SignUpKakaoResponseDto>>

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
     * [signUpNaver]
     * 네이버 회원가입
     * 네이버 회원가입을 진행합니다
     * @since 2023-12-27 14:53:40
     */
    @POST("auth/sign-up/naver")
    suspend fun signUpNaver(
        @Body signUpNaverRequestDto: SignUpNaverRequestDto,
    ): Response<APIResultWrapper<SignUpNaverResponseDto>>


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


    /**
     * [signUpGoogle]
     * 구글 회원가입
     * 구글 회원가입을 진행합니다
     * @since 2023-12-27 14:53:40
     */
    @POST("auth/sign-up/google")
    suspend fun signUpGoogle(
        @Body signUpGoogleRequestDto: SignUpGoogleRequestDto,
    ): Response<APIResultWrapper<SignUpGoogleResponseDto>>



    /**
     * [signInGoogle]
     * 구글 로그인
     * 구글 로그인을 진행하고, 반환된 토큰은 서버에 저장한다.
     * @since 2023-12-26 18:34:19
     */
    @POST("auth/sign-in/google")
    suspend fun signInGoogle(
        @Body signInGoogleRequestDto: SignInGoogleRequestDto,
    ): Response<APIResultWrapper<SignInGoogleResponseDto>>



    /**
     * [checkExistUser]
     * 사용자 아이디를 통해 서버에 사용자 계정이 존재하는지 확인한다.
     * @param userId 확인 할 사용자의 아이디
     * @since 2023-12-26 18:34:22
     */
    @DELETE("auth/exist/user")
    suspend fun checkExistUser(@Query("user_id") userId: String): Response<APIResultWrapper<CheckExistUserResponseDto>>
}

