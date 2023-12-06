package com.gradation.lift.network.datasource.history

import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [HistoryDataSource]
 * 운동 기록과 관련한 데이터 소스
 * @since 2023-08-28 22:08:09
 */
interface HistoryDataSource {
    suspend fun getHistory(): Flow<NetworkResult<List<History>>>

    suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>>
    suspend fun createHistory(createHistory: CreateHistory): Flow<NetworkResult<Unit>>
    suspend fun deleteHistory(historyId: Int): Flow<NetworkResult<Unit>>

}