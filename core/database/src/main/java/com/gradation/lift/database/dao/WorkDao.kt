package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.model.work.WorkEntity
import com.gradation.lift.database.util.Constants

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


    @Query("SELECT * FROM  ${Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME} JOIN ${Constants.Entity.ROUTINE_TABLE_NAME}  ON ${Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME}.id = ${Constants.Entity.ROUTINE_TABLE_NAME}.routine_set_id")
    suspend fun getRoutineSetRoutine(): Map<RoutineSetRoutineEntity, List<RoutineEntity>>

}