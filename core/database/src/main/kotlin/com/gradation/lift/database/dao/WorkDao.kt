package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {


    @Query("SELECT EXISTS(SELECT * FROM work)")
    fun existWork(): Flow<Boolean>

    @Query("SELECT * FROM  $WORK_TABLE_NAME JOIN $WORK_ROUTINE_TABLE_NAME  ON ${WORK_TABLE_NAME}.id = ${WORK_ROUTINE_TABLE_NAME}.work_id")
    fun getAllWork(): Flow<Map<WorkEntity, List<WorkRoutineEntity>>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWork(workEntity: WorkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkRoutine(vararg workRoutineEntity: WorkRoutineEntity)

    @Transaction
    suspend fun insert(workEntity: WorkEntity, workRoutineEntity: List<WorkRoutineEntity>) {
        insertWork(workEntity)
        insertAllWorkRoutine(*workRoutineEntity.toTypedArray())
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWork(workEntity: WorkEntity)


    @Query("DELETE FROM work")
    suspend fun deleteAllWork()


}