package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistory(): Flow<DataState<List<History>>>

    fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<DataState<List<History>>>

    fun createHistory(createHistory: CreateHistory): Flow<DataState<Boolean>>

    fun deleteHistory(historyId: Int): Flow<DataState<Boolean>>
}