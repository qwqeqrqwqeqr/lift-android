package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.model.WorkCategory
import com.gradation.lift.domain.model.WorkPart
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    suspend fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>>
}