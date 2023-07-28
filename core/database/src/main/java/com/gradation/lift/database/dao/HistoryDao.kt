package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.history.HistoryEntity
import com.gradation.lift.database.model.history.HistoryRoutineEntity
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.HISTORY_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHistory(vararg historyEntity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)

    @Query("DELETE FROM '${HISTORY_TABLE_NAME}'")
    suspend fun deleteAllHistory()


    @Query("SELECT * FROM  $HISTORY_TABLE_NAME JOIN $HISTORY_ROUTINE_TABLE_NAME  ON ${HISTORY_TABLE_NAME}.id = ${HISTORY_ROUTINE_TABLE_NAME}.history_id")
    fun getAllHistory(): Flow<List<Map<HistoryEntity, List<HistoryRoutineEntity>>>>


    @Query("SELECT * FROM $HISTORY_TABLE_NAME JOIN $HISTORY_ROUTINE_TABLE_NAME  ON ${HISTORY_TABLE_NAME}.id = ${HISTORY_ROUTINE_TABLE_NAME}.history_id  WHERE id IN (:historyIdList)")
    fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<List<Map<HistoryEntity, List<HistoryRoutineEntity>>>>

}