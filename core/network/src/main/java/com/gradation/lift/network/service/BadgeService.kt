package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.badge.CreateUserBadgeRequestDto
import com.gradation.lift.network.dto.badge.CreateUserBadgeResponseDto
import com.gradation.lift.network.dto.badge.GetBadgeResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeByMainFlagResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeConditionResponseDto
import com.gradation.lift.network.dto.badge.GetUserBadgeResponseDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagRequestDto
import com.gradation.lift.network.dto.badge.UpdateUserBadgeMainFlagResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


/**
 * [BadgeService]
 * 종합적인 뱃지 조회 및 사용자의 뱃지 관리 서비스
 * [getBadge] 전체 뱃지를 불러옵니다.
 * [getUserBadge] 획득한 사용자의 뱃지를 불러옵니다.
 * [createUserBadge] 사용자가 뱃지를 획득합니다
 * [getUserBadgeByMainFlag] 사용자의 대표 뱃지를 불러옵니다.
 * [getUserBadgeByCondition] 뱃지 획득 조건을 만족하는 뱃지가 있는지 확인합니다.
 * @since 2023-09-21 14:17:59
 **/
interface BadgeService {

    @GET("badge/badge")
    suspend fun getBadge(): Response<APIResultWrapper<GetBadgeResponseDto>>

    @GET("badge/user-badge")
    suspend fun getUserBadge(): Response<APIResultWrapper<GetUserBadgeResponseDto>>

    @POST("badge/user-badge")
    suspend fun createUserBadge(@Body createUserBadgeRequestDto: CreateUserBadgeRequestDto): Response<APIResultWrapper<CreateUserBadgeResponseDto>>

    @GET("badge/user-badge/main-flag")
    suspend fun getUserBadgeByMainFlag(): Response<APIResultWrapper<GetUserBadgeByMainFlagResponseDto>>

    @GET("badge/user-badge/condition")
    suspend fun getUserBadgeByCondition(): Response<APIResultWrapper<GetUserBadgeConditionResponseDto>>

    @PUT("badge/user-badge/main-flag")
    suspend fun updateUserBadgeMainFlag(@Body updateUserBadgeMainFlagRequestDto: UpdateUserBadgeMainFlagRequestDto): Response<APIResultWrapper<UpdateUserBadgeMainFlagResponseDto>>
}