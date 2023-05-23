package com.gradation.lift.domain.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.WorkCategory
import com.gradation.lift.domain.model.WorkPart
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>>
}