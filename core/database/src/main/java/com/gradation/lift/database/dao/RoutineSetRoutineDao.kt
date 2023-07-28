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
    suspend fun insertRoutine(routineEntity: RoutineEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutineSetRoutine(vararg routineSetRoutineEntity: RoutineSetRoutineEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutine(vararg routineEntity: RoutineEntity)

    @Transaction
    suspend fun insert(routineSetRoutineEntity: RoutineSetRoutineEntity, routineEntity: RoutineEntity){
        insertRoutineSetRoutine(routineSetRoutineEntity)
        insertRoutine(routineEntity)
    }

    @Transaction
    suspend fun insertAll(routineSetRoutineEntity: List<RoutineSetRoutineEntity>, routineEntity: List<RoutineEntity>){
        insertAllRoutineSetRoutine(*routineSetRoutineEntity.toTypedArray())
        insertAllRoutine(*routineEntity.toTypedArray())
    }


    @Delete
    suspend fun deleteRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Query("DELETE FROM $ROUTINE_SET_ROUTINE_TABLE_NAME")
    suspend fun deleteAllRoutineSetRoutine()

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id")
    fun getAllRoutineSetRoutine(): Flow<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id WHERE $ROUTINE_SET_ROUTINE_TABLE_NAME.id IN (:routineSetIdList)")
    fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>

    @Query("SELECT * FROM  $ROUTINE_SET_ROUTINE_TABLE_NAME JOIN $ROUTINE_TABLE_NAME  ON $ROUTINE_SET_ROUTINE_TABLE_NAME.id = $ROUTINE_TABLE_NAME.routine_set_id WHERE $ROUTINE_SET_ROUTINE_TABLE_NAME.weekday = :weekday")
    fun getRoutineSetRoutineByWeekday(weekday: String): Flow<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>


}