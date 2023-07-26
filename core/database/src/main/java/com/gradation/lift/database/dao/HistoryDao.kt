package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.history.HistoryEntity
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.util.Constants

@Dao
interface HistoryDao {


    @Insert
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHistory(historyEntity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)



    @Query("DELETE FROM '${Constants.Entity.HISTORY_TABLE_NAME}'")
    suspend fun deleteAllHistory()


    @Query("SELECT * FROM  ${Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME} JOIN ${Constants.Entity.ROUTINE_TABLE_NAME}  ON ${Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME}.id = ${Constants.Entity.ROUTINE_TABLE_NAME}.routine_set_id")
    suspend fun getRoutineSetRoutine(): Map<RoutineSetRoutineEntity, List<RoutineEntity>>
}