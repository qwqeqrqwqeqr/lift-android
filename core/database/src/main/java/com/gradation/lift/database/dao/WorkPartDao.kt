package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.database.model.WorkEntity
import com.gradation.lift.database.model.WorkPartEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_PART_TABLE_NAME
import com.gradation.lift.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

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