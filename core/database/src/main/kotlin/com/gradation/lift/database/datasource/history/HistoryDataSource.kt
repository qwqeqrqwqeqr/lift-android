package com.gradation.lift.database.datasource.history

import com.gradation.lift.model.model.history.History
import kotlinx.coroutines.flow.Flow

/**
 * [getAllHistory] 모든 기록 조회
 * [fetch] 패치
 * @since 2024-03-06 16:56:43
 */
interface HistoryDataSource {

    fun getAllHistory(): Flow<List<History>>

    suspend fun fetch(history: List<History>)
}