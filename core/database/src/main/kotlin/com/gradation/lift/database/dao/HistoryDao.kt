package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.HISTORY_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistory(vararg historyEntity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistoryRoutine(vararg historyRoutineEntity: HistoryRoutineEntity)

    suspend fun insertAll(
        historyEntity: List<HistoryEntity>,
        historyRoutineEntity: List<HistoryRoutineEntity>
    ) {
        insertAllHistory(*historyEntity.toTypedArray())
        insertAllHistoryRoutine(*historyRoutineEntity.toTypedArray())
    }


    @Query("DELETE FROM '${HISTORY_TABLE_NAME}'")
    suspend fun deleteAllHistory()


    @Query("SELECT * FROM  $HISTORY_TABLE_NAME JOIN $HISTORY_ROUTINE_TABLE_NAME  ON ${HISTORY_TABLE_NAME}.id = ${HISTORY_ROUTINE_TABLE_NAME}.history_id")
    fun getAllHistory(): Flow<Map<HistoryEntity, List<HistoryRoutineEntity>>>


    @Query("SELECT * FROM $HISTORY_TABLE_NAME JOIN $HISTORY_ROUTINE_TABLE_NAME  ON ${HISTORY_TABLE_NAME}.id = ${HISTORY_ROUTINE_TABLE_NAME}.history_id  WHERE $HISTORY_TABLE_NAME.id IN (:historyIdList)")
    fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<Map<HistoryEntity, List<HistoryRoutineEntity>>>

}