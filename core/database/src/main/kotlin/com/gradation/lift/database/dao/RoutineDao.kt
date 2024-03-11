package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM  routine_set_routine JOIN routine  ON routine_set_routine.id = routine.routine_set_id")
    fun getAllRoutineSetRoutine(): Flow<Map<RoutineSetRoutineEntity, List<RoutineEntity>>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutineSetRoutine(vararg routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutine(vararg routineEntity: RoutineEntity)


    @Query("DELETE FROM routine_set_routine")
    suspend fun deleteAllRoutineSetRoutine()

    suspend fun insertAll(
        routineSetRoutineEntity: List<RoutineSetRoutineEntity>,
        routineEntity: List<RoutineEntity>,
    ) {
        insertAllRoutineSetRoutine(*routineSetRoutineEntity.toTypedArray())
        insertAllRoutine(*routineEntity.toTypedArray())
    }

}