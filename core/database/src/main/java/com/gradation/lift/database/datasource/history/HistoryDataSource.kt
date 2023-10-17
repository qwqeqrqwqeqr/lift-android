package com.gradation.lift.database.datasource.history

import com.gradation.lift.model.model.history.History
import kotlinx.coroutines.flow.Flow

/**
 * [getAllHistory] 모든 기록 조회
 * [deleteAllHistory] 모든 기록 삭제
 * [insertAllHistory] 모든 기록 추가
 * @since 2023-10-15 18:20:47
 */
interface HistoryDataSource {

    suspend fun getAllHistory(): Flow<List<History>>
    suspend fun deleteAllHistory()
    suspend fun insertAllHistory(
        history: List<History>,
    )

    suspend fun fetchHistory(history: List<History>)
}