package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.database.model.WorkEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {


    @Insert
    suspend fun insertWork(workEntity: WorkEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWork(workEntity: WorkEntity)

    @Delete
    suspend fun deleteWork(workEntity: WorkEntity)


    @Query("DELETE FROM '${Constants.Entity.WORK_TABLE_NAME}'")
    suspend fun deleteAllWork()



}