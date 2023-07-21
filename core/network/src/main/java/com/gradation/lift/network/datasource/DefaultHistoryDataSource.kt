package com.gradation.lift.network.datasource

import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.History
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.HistoryService
import com.gradation.lift.network.service.RoutineService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHistoryDataSource @Inject constructor(
    private val historyService: HistoryService,
    private val networkResultHandler: NetworkResultHandler,
) : HistoryDataSource {
    override suspend fun getHistory(): Flow<APIResult<History>> = flow{
        networkResultHandler{
            historyService.getHistory()
        }
    }

    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<APIResult<History>> = flow{
    }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<APIResult<Boolean>> = flow{
    }

    override suspend fun deleteHistory(historyId: Int): Flow<APIResult<Boolean>> = flow {
    }

}