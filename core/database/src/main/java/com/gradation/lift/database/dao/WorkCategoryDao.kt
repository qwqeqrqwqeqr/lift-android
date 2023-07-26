package com.gradation.lift.database.dao

import androidx.room.*
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.database.model.WorkEntity
import com.gradation.lift.database.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkCategoryDao {


    @Insert
    suspend fun insertWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkCategory(workCategoryEntity: WorkCategoryEntity)

    @Delete
    suspend fun deleteWorkCategory(workCategoryEntity: WorkCategoryEntity)



    @Query("DELETE FROM '${Constants.Entity.WORK_CATEGORY_TABLE_NAME}'")
    suspend fun deleteAllWorkCategory()


}