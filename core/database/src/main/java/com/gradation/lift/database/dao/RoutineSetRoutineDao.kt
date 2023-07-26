package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_TABLE_NAME

@Dao
interface RoutineSetRoutineDao {


    @Insert
    suspend fun insertRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Delete
    suspend fun deleteRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Query("DELETE FROM $ROUTINE_SET_ROUTINE_TABLE_NAME")
    suspend fun deleteAllRoutineSetRoutine()


    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id")
    suspend fun getRoutineSetRoutine(): Map<RoutineSetRoutineEntity, List<RoutineEntity>>
}