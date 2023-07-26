package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>>
}