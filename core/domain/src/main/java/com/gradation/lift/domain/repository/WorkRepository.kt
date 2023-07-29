package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>>

    fun getWork(): Flow<DataState<Work>>

    fun updateWork(work: Work): Flow<DataState<Unit>>

    fun deleteWork(work: Work): Flow<DataState<Unit>>

    fun existWork(): Flow<DataState<Boolean>>
}