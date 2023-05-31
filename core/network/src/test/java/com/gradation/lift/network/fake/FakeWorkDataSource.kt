package com.gradation.lift.network.fake

import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.WorkService
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class FakeWorkDataSource @Inject constructor(
    private val workService: WorkService,
    private val networkResultHandler: NetworkResultHandler
) : WorkDataSource {
    override suspend fun getWorkPart(): Flow<APIResult<List<WorkPart>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategory(): Flow<APIResult<List<WorkCategory>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): Flow<APIResult<List<WorkCategory>>> {
        TODO("Not yet implemented")
    }

}