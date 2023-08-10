package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [WorkService]
 * 운동 정보 서비스
 */
interface WorkService {
    /**
     * [getWorkPart]
     * 모든 운동 부위 정보 불러오기
     */
    @GET("work/work-part/")
    suspend fun getWorkPart(): Response<APIResultWrapper<GetWorkPartResponseDto>>

    /**
     * [getWorkPart]
     * 모든 운동 카테고리 정보 불러오기
     */
    @GET("work/work-category/")
    suspend fun getWorkCategory(): Response<APIResultWrapper<GetWorkCategoryResponseDto>>

    /**
     * [getWorkPart]
     * 운동 부위에 맞는 운동 카테고리 정보 불러오기
     */
    @GET("work/work-category-by-work-part/")
    suspend fun getWorkCategoryByWorkPart(@Query("work_part") workPart: String): Response<APIResultWrapper<GetWorkCategoryByWorkPartResponseDto>>
}



