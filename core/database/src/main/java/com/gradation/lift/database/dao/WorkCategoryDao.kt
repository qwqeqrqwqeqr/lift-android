package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkCategory(vararg workCategoryEntity: WorkCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)


    @Query("DELETE FROM '${WORK_CATEGORY_TABLE_NAME}'")
    suspend fun deleteAllWorkCategory()


    @Query("SELECT * FROM `${WORK_CATEGORY_TABLE_NAME}` WHERE  work_part_name = :workPart")
    fun getWorkCategoryByWorkPart(workPart : String) : Flow<WorkCategoryEntity>

    @Query("SELECT * FROM `${WORK_CATEGORY_TABLE_NAME}`")
    fun getAllWorkCategory() : Flow<List<WorkCategoryEntity>>
}