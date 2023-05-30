package com.gradation.lift.network.datasource

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.network.common.APIResult

interface WorkDataSource {
    suspend fun getWorkPart(): APIResult<List<WorkPart>>

    suspend fun getWorkCategory(): APIResult<List<WorkCategory>>

    suspend fun getWorkCategoryByWorkPart(workpart: Int): APIResult<List<WorkCategory>>
}