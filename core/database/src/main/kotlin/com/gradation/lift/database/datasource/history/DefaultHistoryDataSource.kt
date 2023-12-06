package com.gradation.lift.database.datasource.history

import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.history.History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHistoryDataSource @Inject constructor(
    private val historyDao: HistoryDao,
) : HistoryDataSource {
    override suspend fun getAllHistory(): Flow<List<History>> = flow {
        historyDao.getAllHistory().collect { historyEntity ->
            emit(
                historyEntity.map {
                    it.key.toDomain().copy(historyRoutine = it.value.map { it.toDomain() })
                }
            )
        }
    }

    override suspend fun deleteAllHistory() {
        historyDao.deleteAllHistory()
    }

    override suspend fun insertAllHistory(
        history: List<History>,
    ) {

        historyDao.insertAll(
            historyEntity = history.map { it.toEntity() },
            historyRoutineEntity = history.flatMap { it.historyRoutine.map { it.toEntity() } }
        )
    }

    override suspend fun fetchHistory(history: List<History>) {
        historyDao.deleteAllHistory()
        historyDao.insertAll(
            historyEntity = history.map { it.toEntity() },
            historyRoutineEntity = history.flatMap { it.historyRoutine.map { it.toEntity() } }
        )
    }

}