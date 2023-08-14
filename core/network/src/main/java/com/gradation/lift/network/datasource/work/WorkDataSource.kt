package com.gradation.lift.network.datasource.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WorkDataSource {
    suspend fun getWorkPart(): Flow<NetworkResult<List<WorkPart>>>

    suspend fun getWorkCategory(): Flow<NetworkResult<List<WorkCategory>>>

    suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<NetworkResult<List<WorkCategory>>>
}