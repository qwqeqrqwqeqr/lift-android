package com.gradation.lift.data.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
) : WorkRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {

    }

    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {

    }

    override fun getWorkCategoryByWorkPart(workpart: Int): Flow<DataState<List<WorkCategory>>> =
        flow {

        }
}