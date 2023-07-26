package com.gradation.lift.network.datasource

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface WorkDataSource {
    suspend fun getWorkPart(): Flow<APIResult<List<WorkPart>>>

    suspend fun getWorkCategory(): Flow<APIResult<List<WorkCategory>>>

    suspend fun getWorkCategoryByWorkPart(workPart: String): Flow<APIResult<List<WorkCategory>>>
}