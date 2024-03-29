package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.auth.RefreshResponseDto
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * [RefreshService]
 * 토큰 갱신 서비스
 */
interface RefreshService {


    /**
     * [refresh]
     * 토큰 갱신
     * @param Authorization 토큰을 갱신하기위해 필요한 refresh Token
     * @since 2023-08-28 22:32:01
     */
    @POST("auth/refresh")
    suspend fun refresh(
        @Header("Authorization") Authorization: String,
    ): Response<APIResultWrapper<RefreshResponseDto>>
}