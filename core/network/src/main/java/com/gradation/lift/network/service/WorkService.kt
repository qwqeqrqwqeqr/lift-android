package com.gradation.lift.network.service

import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkService {
    @GET("work/work-part/")
    suspend fun getWorkPart(): Response<Result<List<GetWorkPartResponseDto>>>

    @GET("work/work-category/")
    suspend fun getWorkCategory(): Response<Result<List<GetWorkCategoryResponseDto>>>

    @GET("work/work-category-by-work-part/")
    suspend fun getWorkCategoryByWorkPart(@Query("workpart") workpart: Int): Response<Result<List<GetWorkCategoryByWorkPartResponseDto>>>
}



