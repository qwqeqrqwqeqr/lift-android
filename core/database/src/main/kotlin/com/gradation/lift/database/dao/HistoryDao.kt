package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {


    @Query("SELECT * FROM  history JOIN history_routine  ON history.id = history_routine.history_id")
    fun getAllHistory(): Flow<Map<HistoryEntity, List<HistoryRoutineEntity>>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistory(vararg historyEntity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistoryRoutine(vararg historyRoutineEntity: HistoryRoutineEntity)


    @Transaction
    suspend fun insertAll(
        historyEntity: List<HistoryEntity>,
        historyRoutineEntity: List<HistoryRoutineEntity>,
    ) {
        insertAllHistory(*historyEntity.toTypedArray())
        insertAllHistoryRoutine(*historyRoutineEntity.toTypedArray())
    }


    @Query("DELETE FROM history")
    suspend fun deleteAllHistory()

    @Query("update history set  comment=:comment ,score=:score where id=:historyId;")
    suspend fun updateHistoryInfo(historyId: Int, comment: String?, score: Int?)

}