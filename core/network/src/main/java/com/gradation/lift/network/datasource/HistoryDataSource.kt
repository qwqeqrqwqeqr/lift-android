package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {
    suspend fun getHistory(): Flow<APIResult<List<History>>>

    suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<APIResult<List<History>>>
    suspend fun createHistory(createHistory: CreateHistory): Flow<APIResult<Boolean>>
    suspend fun deleteHistory(historyId: Int): Flow<APIResult<Boolean>>

}