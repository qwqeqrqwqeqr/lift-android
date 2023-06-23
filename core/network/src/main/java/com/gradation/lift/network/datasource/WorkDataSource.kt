package com.gradation.lift.network.datasource

import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.AuthAPIResult
import kotlinx.coroutines.flow.Flow

interface WorkDataSource {
    suspend fun getWorkPart(): Flow<AuthAPIResult<List<WorkPart>>>

    suspend fun getWorkCategory(): Flow<AuthAPIResult<List<WorkCategory>>>

    suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<AuthAPIResult<List<WorkCategory>>>
}