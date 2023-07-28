package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.history.HistoryEntity
import com.gradation.lift.database.model.history.HistoryRoutineEntity
import com.gradation.lift.database.model.work.WorkEntity
import com.gradation.lift.database.model.work.WorkRoutineEntity
import com.gradation.lift.database.model.work_category.WorkPartEntity
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

    @Transaction
    suspend fun insert(workEntity: WorkEntity, workRoutineEntity: WorkRoutineEntity){
        insertWork(workEntity)
        insertWorkRoutine(workRoutineEntity)
    }
    @Transaction
    suspend fun insertAll(workEntity: List<WorkEntity>, workRoutineEntity: List<WorkRoutineEntity>){
        insertAllWork(*workEntity.toTypedArray())
        insertAllWorkRoutine(*workRoutineEntity.toTypedArray())
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWork(workEntity: WorkEntity)

    @Delete
    suspend fun deleteWork(workEntity: WorkEntity)

    @Query("DELETE FROM '${WORK_TABLE_NAME}'")
    suspend fun deleteAllWork()

    @Query("SELECT * FROM  $WORK_TABLE_NAME JOIN $WORK_ROUTINE_TABLE_NAME  ON ${WORK_TABLE_NAME}.id = ${WORK_ROUTINE_TABLE_NAME}.work_id")
    fun getAllWork(): Flow<Map<WorkEntity, List<WorkRoutineEntity>>>

}