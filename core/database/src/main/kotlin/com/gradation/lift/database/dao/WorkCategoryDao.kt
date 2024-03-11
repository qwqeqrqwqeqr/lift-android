package com.gradation.lift.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {

    @Query("SELECT * FROM work_category")
    fun getAllWorkCategory(): Flow<List<WorkCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWorkCategory(vararg workCategoryEntity: WorkCategoryEntity)

    @Query("DELETE FROM work_category")
    suspend fun deleteAllWorkCategory()


}