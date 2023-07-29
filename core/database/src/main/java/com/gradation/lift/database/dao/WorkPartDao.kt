package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.model.work_category.WorkPartEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.Constants.Entity.WORK_PART_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkPartDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkPart(workPartEntity: WorkPartEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkPart(vararg workPartEntity: WorkPartEntity)

    @Delete
    suspend fun deleteWorkPart(workPartEntity: WorkPartEntity)
    @Query("DELETE FROM '${WORK_PART_TABLE_NAME}'")
    suspend fun deleteAllWorkPart()


    @Query("SELECT * FROM `${WORK_PART_TABLE_NAME}`")
    fun getAllWorkPart() : Flow<List<WorkPartEntity>>
}