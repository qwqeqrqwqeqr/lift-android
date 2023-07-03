package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("auth/sign-up/default/")
    suspend fun signUpDefault(@Body signUpDefaultRequestDto: SignUpDefaultRequestDto,
    ): APIResultWrapper<SignUpDefaultResponseDto>


    @POST("auth/sign-in/default/")
    suspend fun signInDefault(@Body signInDefaultRequestDto: SignInDefaultRequestDto,
    ): APIResultWrapper<SignInDefaultResponseDto>


    @GET("auth/check-duplicate-email/")
    suspend fun checkDuplicateEmail(@Body checkDuplicateEmailRequestDto: CheckDuplicateEmailRequestDto,
    ): APIResultWrapper<CheckDuplicateEmailResponseDto>


}

