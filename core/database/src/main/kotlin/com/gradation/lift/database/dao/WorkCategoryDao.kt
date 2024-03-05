package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkCategory(vararg workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Query("DELETE FROM '${WORK_CATEGORY_TABLE_NAME}'")
    suspend fun deleteAllWorkCategory()

    @Query("SELECT * FROM `${WORK_CATEGORY_TABLE_NAME}`")
    fun getAllWorkCategory(): Flow<List<WorkCategoryEntity>>
}