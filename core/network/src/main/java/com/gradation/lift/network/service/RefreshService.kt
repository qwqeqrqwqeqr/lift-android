package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.RefreshResult
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface RefreshService {
    @GET("auth/refresh/")
    suspend fun refresh(
        @Header("Authorization") refreshToken: String,
    ): RefreshResult<RefreshResponseDto>
}