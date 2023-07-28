package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.history.HistoryEntity
import com.gradation.lift.database.model.history.HistoryRoutineEntity
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineSetRoutineDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutineSetRoutine(vararg routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Delete
    suspend fun deleteRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Query("DELETE FROM $ROUTINE_SET_ROUTINE_TABLE_NAME")
    suspend fun deleteAllRoutineSetRoutine()

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id")
    fun getAllRoutineSetRoutine(): Flow<List<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>>

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id WHERE id IN (:routineSetIdList)")
    fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<List<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>>

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id WHERE weekday = :weekday")
    fun getRoutineSetRoutineByWeekday(weekday: String): Flow<List<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>>


}