package com.gradation.lift.database.dao

import androidx.room.*

import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.model.data.Week
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineSetDao {

    @Insert
    suspend fun insertRoutineSet(routineSetEntity: RoutineSetEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoutineSet(routineSetEntity: RoutineSetEntity)

    @Delete
    suspend fun deleteRoutineSet(routineSetEntity: RoutineSetEntity)


    /*
        요일에 따른 루틴을 불러옵니다.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM 'routine_sets'
            WHERE  'week' = :week
    """
    )
     fun getAllRoutineSetEntriesByWeek(week: String): Flow<List<RoutineSetEntity>>


    /*
      전체 루틴을 불러옵니다.
   */
    @Transaction
    @Query(
        value = """
            SELECT * FROM 'routine_sets'
    """
    )
     fun getAllRoutineSetEntries(): Flow<List<RoutineSetEntity>>
}


