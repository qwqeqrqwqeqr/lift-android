package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import com.gradation.lift.network.dto.auth.SignInRequestDto
import com.gradation.lift.network.dto.auth.SignInResponseDto
import com.gradation.lift.network.dto.routine.GetRoutineSetResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("auth/sign-in/")
    suspend fun signIn(@Body signInRequestDto: SignInRequestDto,
    ): APIResultWrapper<SignInResponseDto>




}

