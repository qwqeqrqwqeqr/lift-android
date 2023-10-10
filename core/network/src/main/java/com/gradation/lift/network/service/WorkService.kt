package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.work.GetPopularWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetRecommendWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [WorkService]
 * 운동 정보 서비스
 * @since 2023-08-28 22:29:45
 */
interface WorkService {
    /**
     * [getWorkPart]
     * 모든 운동 부위 정보 불러오기
     * @since 2023-08-28 22:29:40
     */
    @GET("work/work-part/")
    suspend fun getWorkPart(): Response<APIResultWrapper<GetWorkPartResponseDto>>

    /**
     * [getWorkPart]
     * 모든 운동 카테고리 정보 불러오기
     * @since 2023-08-28 22:29:35
     */
    @GET("work/work-category/")
    suspend fun getWorkCategory(): Response<APIResultWrapper<GetWorkCategoryResponseDto>>


    /**
     * [getPopularWorkCategory]
     * 인기 있는 운동 카테고리 정보 불러오기
     * @since 2023-09-21 14:22:16
     */
    @GET("work/work-category/popular")
    suspend fun getPopularWorkCategory(): Response<APIResultWrapper<GetPopularWorkCategoryResponseDto>>

    /**
     * [getRecommendWorkCategory]
     * 추천 운동 카테고리 정보 불러오기
     * @since 2023-10-06 22:23:58
     */
    @GET("work/work-category/recommend")
    suspend fun getRecommendWorkCategory(): Response<APIResultWrapper<GetRecommendWorkCategoryResponseDto>>

    /**
     * [getWorkPart]
     * 운동 부위에 맞는 운동 카테고리 정보 불러오기
     * @since 2023-08-28 22:29:31
     */
    @GET("work/work-category-by-work-part/")
    suspend fun getWorkCategoryByWorkPart(@Query("work_part") workPart: String): Response<APIResultWrapper<GetWorkCategoryByWorkPartResponseDto>>
}



