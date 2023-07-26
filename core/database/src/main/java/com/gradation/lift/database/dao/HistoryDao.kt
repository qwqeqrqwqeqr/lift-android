package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.history.HistoryEntity
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

}