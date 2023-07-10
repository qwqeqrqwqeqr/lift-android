package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.checker.CheckDuplicateEmailResponseDto
import com.gradation.lift.network.dto.checker.CheckDuplicateNameResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckerService {


    @GET("checker/duplicate-email/")
    suspend fun checkDuplicateEmail(@Query("email") email: String ): Response<APIResultWrapper<CheckDuplicateEmailResponseDto>>


    @GET("checker/duplicate-name/")
    suspend fun checkDuplicateName(@Query("name") name: String): Response<APIResultWrapper<CheckDuplicateNameResponseDto>>

}