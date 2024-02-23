package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.favorite.GetWorkCategoryFavoriteResponseDto
import com.gradation.lift.network.dto.favorite.UpdateWorkCategoryFavoriteRequestDto
import com.gradation.lift.network.dto.favorite.UpdateWorkCategoryFavoriteResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * [FavoriteService]
 * 관심등록을 관리하는 서비스
 * @since 2024-02-23 15:00:25
 */
interface FavoriteService {


    /**
     * [getWorkCategoryFavorite]
     * 사용자가 관심 등록한 운동 카테고리 불러오기
     * @since 2024-02-23 15:06:25
     */
    @GET("favorite/work-category")
    suspend fun getWorkCategoryFavorite(): Response<APIResultWrapper<GetWorkCategoryFavoriteResponseDto>>

    /**
     * [updateWorkCategoryFavorite]
     * 사용자가 관심 등록한 운동 카테고리 업데이트하기 (on/off 방식)
     * @since 2024-02-23 15:06:25
     */
    @POST("favorite/work-category")
    suspend fun updateWorkCategoryFavorite(@Body updateWorkCategoryFavoriteRequestDto: UpdateWorkCategoryFavoriteRequestDto): Response<APIResultWrapper<UpdateWorkCategoryFavoriteResponseDto>>

}