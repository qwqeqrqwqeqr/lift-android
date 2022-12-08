package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.model.data.WorkPart
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)


    @Query("DELETE FROM 'work_categories'")
    suspend fun deleteAllWorkCategory()



    @Transaction
    @Query("SELECT * FROM work_categories WHERE id=:id")
    fun getWorkCategoryById(id: Long): Flow<WorkCategoryEntity>

    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work_categories'
            WHERE  `custom_flag` = :customFlag and `work_part` = :workPart
    """
    )
     fun getAllWorkCategoryEntriesByWorkPartCustomFlag(
        workPart: WorkPart,
        customFlag: Boolean
    ): Flow<List<WorkCategoryEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM 'work_categories'
            """
    )
     fun getAllWorkCategory(): Flow<List<WorkCategoryEntity>>

}