package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkService {
    @GET("work/work-part/")
    suspend fun getWorkPart(): Response<APIResultWrapper<GetWorkPartResponseDto>>

    @GET("work/work-category/")
    suspend fun getWorkCategory(): Response<APIResultWrapper<GetWorkCategoryResponseDto>>

    @GET("work/work-category-by-work-part/")
    suspend fun getWorkCategoryByWorkPart(@Query("workpart") workpart: Int): Response<APIResultWrapper<GetWorkCategoryByWorkPartResponseDto>>
}



