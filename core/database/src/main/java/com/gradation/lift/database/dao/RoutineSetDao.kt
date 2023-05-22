package com.gradation.lift.database.dao

import androidx.room.*

import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.domain.model.Week
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineSetDao {

    @Insert
    suspend fun insertRoutineSet(routineSetEntity: RoutineSetEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoutineSet(routineSetEntity: RoutineSetEntity)

    @Delete
    suspend fun deleteRoutineSet(routineSetEntity: RoutineSetEntity)


    @Query("DELETE FROM 'routine_sets'")
    suspend fun deleteAllRoutineSet()

    /*
        요일에 따른 루틴을 불러옵니다.
     */
    @Transaction
    @Query("SELECT * FROM routine_sets WHERE week=:week")
    fun getAllRoutineSetByWeek(week: com.gradation.lift.domain.model.Week): Flow<List<RoutineSetEntity>>


    /*
       고유 아이디에 따른 루틴을 불러옵니다.
     */
    @Transaction
    @Query("SELECT * FROM routine_sets WHERE id=:id")
    fun getRoutineSetById(id: Long): Flow<RoutineSetEntity>

    /*
      전체 루틴을 불러옵니다.
   */
    @Transaction
    @Query(
        value = """
            SELECT * FROM 'routine_sets'
    """
    )
    fun getAllRoutineSet(): Flow<List<RoutineSetEntity>>
}


