package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.RoutineSetRoutineEntity
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.database.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineSetRoutineDao {


    @Insert
    suspend fun insertRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)

    @Delete
    suspend fun deleteRoutineSetRoutine(routineSetRoutineEntity: RoutineSetRoutineEntity)


    @Query("DELETE FROM '${Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME}'")
    suspend fun deleteAllRoutineSetRoutine()

}