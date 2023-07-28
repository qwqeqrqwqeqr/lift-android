package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.work.WorkEntity
import com.gradation.lift.database.model.work.WorkRoutineEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Insert
    suspend fun insertWork(workEntity: WorkEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWork(workEntity: WorkEntity)

    @Delete
    suspend fun deleteWork(workEntity: WorkEntity)


    @Query("DELETE FROM '${WORK_TABLE_NAME}'")
    suspend fun deleteAllWork()

    @Query("SELECT * FROM  ${WORK_TABLE_NAME} JOIN ${WORK_ROUTINE_TABLE_NAME}  ON ${WORK_TABLE_NAME}.id = ${WORK_ROUTINE_TABLE_NAME}.work_id")
    fun getAllWork(): Map<WorkEntity, List<WorkRoutineEntity>>


}