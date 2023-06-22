package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import com.gradation.lift.network.dto.auth.SignInRequestDto
import com.gradation.lift.network.dto.auth.SignInResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {
    @GET("auth/sign-in/")
    suspend fun signIn(
        signInRequestDto: SignInRequestDto,
    ): APIResultWrapper<SignInResponseDto>


    @GET("auth/refresh/")
    suspend fun refresh(
        @Header("Authorization") refreshToken: String,
    ): APIResultWrapper<RefreshResponseDto>

}

