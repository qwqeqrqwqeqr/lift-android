package com.gradation.lift.network.datasource.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [WorkDataSource]
 * 운동 카테고리 및 운동 부위와 관련한 데이터 소스
 * @since 2023-08-28 22:09:58
 */
interface WorkDataSource {
    suspend fun getWorkPart(): Flow<NetworkResult<List<WorkPart>>>

    suspend fun getWorkCategory(): Flow<NetworkResult<List<WorkCategory>>>
    suspend fun getWorkCategoryById(workCategoryId:Int): Flow<NetworkResult<WorkCategory>>


    suspend fun getPopularWorkCategory(): Flow<NetworkResult<List<WorkCategory>>>
    suspend fun getRecommendWorkCategory(): Flow<NetworkResult<List<WorkCategory>>>

    suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<NetworkResult<List<WorkCategory>>>
}