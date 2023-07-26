package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.work_category.WorkPartEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_PART_TABLE_NAME

@Dao
interface WorkPartDao {


    @Insert
    suspend fun insertWorkPart(workPartEntity: WorkPartEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkPart(workPartEntity: WorkPartEntity)

    @Delete
    suspend fun deleteWorkPart(workPartEntity: WorkPartEntity)


    @Query("DELETE FROM '${WORK_PART_TABLE_NAME}'")
    suspend fun deleteAllWorkPart()

}