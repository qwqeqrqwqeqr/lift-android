package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import com.gradation.lift.network.service.WorkService
import retrofit2.Response
import javax.inject.Inject

interface WorkDataSource {
    suspend fun  getWorkPart(): Response<APIResult<List<GetWorkPartResponseDto>>>
    suspend fun getWorkCategory(): Response<APIResult<List<GetWorkCategoryResponseDto>>>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): Response<APIResult<List<GetWorkCategoryByWorkPartResponseDto>>>
}


class WorkDataSourceImpl @Inject constructor(private val workService: WorkService):WorkDataSource{
    override suspend fun getWorkPart(): Response<APIResult<List<GetWorkPartResponseDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategory(): Response<APIResult<List<GetWorkCategoryResponseDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Response<APIResult<List<GetWorkCategoryByWorkPartResponseDto>>> {
        TODO("Not yet implemented")
    }

}