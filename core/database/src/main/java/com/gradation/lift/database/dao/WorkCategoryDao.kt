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

    @Query("delete from `work-categories`")
    suspend fun deleteAll()

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

    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work-categories'
            """
    )
    fun getWorkCategoryEntries(): Flow<List<WorkCategoryEntity>>

}