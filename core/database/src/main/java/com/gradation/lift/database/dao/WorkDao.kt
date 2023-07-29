package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWork(workEntity: WorkEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkRoutine(workRoutineEntity: WorkRoutineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWork(vararg workEntity: WorkEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkRoutine(vararg workRoutineEntity: WorkRoutineEntity)


    suspend fun insert(workEntity: WorkEntity, workRoutineEntity: List<WorkRoutineEntity>){
        insertWork(workEntity)
        insertAllWorkRoutine(*workRoutineEntity.toTypedArray())

    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkRoutine(workRoutineEntity: WorkRoutineEntity)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWork(workEntity: WorkEntity)

    @Delete
    suspend fun deleteWork(workEntity: WorkEntity)

    @Query("DELETE FROM '${WORK_TABLE_NAME}'")
    suspend fun deleteAllWork()

    @Query("SELECT * FROM  $WORK_TABLE_NAME JOIN $WORK_ROUTINE_TABLE_NAME  ON ${WORK_TABLE_NAME}.id = ${WORK_ROUTINE_TABLE_NAME}.work_id")
    fun getAllWork(): Flow<Map<WorkEntity, List<WorkRoutineEntity>>>

}