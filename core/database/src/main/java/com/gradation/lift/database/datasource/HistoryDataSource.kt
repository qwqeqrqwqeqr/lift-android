package com.gradation.lift.database.datasource

import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
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
        historyEntity: List<HistoryEntity>,
        historyRoutineEntity: List<HistoryRoutineEntity>
    )
}