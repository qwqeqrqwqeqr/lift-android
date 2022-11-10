package com.gradation.database.dao

import androidx.room.*
import com.gradation.database.model.RoutineSetEntity
import com.gradation.database.model.WorkCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert(onConflict = OnConflictStrategy.FAIL)
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work-categories'
            WHERE  `custom-flag` = :customFlag and `work-part` = :workPart
    """
    )
    fun getAllWorkCategoryEntriesByWorkPartCustomFlag(
        workPart: String,
        customFlag: Boolean
    ): Flow<List<WorkCategoryEntity>>


    @Query("SELECT * FROM  `work-categories` ")
    suspend fun getWorkCategoryEntries(): Flow<List<WorkCategoryEntity>>?

}