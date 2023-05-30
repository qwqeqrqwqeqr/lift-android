package com.gradation.lift.network.datasource_impl

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.service.WorkService
import javax.inject.Inject

class DefaultWorkDataSource @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler
) : WorkDataSource {
    override suspend fun getWorkPart(): APIResult<List<WorkPart>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategory(): APIResult<List<WorkCategory>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): APIResult<List<WorkCategory>> {
        TODO("Not yet implemented")
    }
}