package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.database.model.WorkCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)



    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work_categories'
            WHERE  `custom_flag` = :customFlag and `work_part` = :workPart
    """
    )
     fun getAllWorkCategoryEntriesByWorkPartCustomFlag(
        workPart: String,
        customFlag: Boolean
    ): Flow<List<WorkCategoryEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work_categories'
            """
    )
     fun getWorkCategoryEntries(): Flow<List<WorkCategoryEntity>>

}