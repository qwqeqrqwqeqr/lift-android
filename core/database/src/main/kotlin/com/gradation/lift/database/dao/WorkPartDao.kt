package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_PART_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkPartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkPart(vararg workPartEntity: WorkPartEntity)

    @Query("DELETE FROM '${WORK_PART_TABLE_NAME}'")
    suspend fun deleteAllWorkPart()

    @Query("SELECT * FROM `${WORK_PART_TABLE_NAME}`")
    fun getAllWorkPart() : Flow<List<WorkPartEntity>>
}