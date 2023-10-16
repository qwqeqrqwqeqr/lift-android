package com.gradation.lift.database.datasource.work_category

import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow

/**
 * [insertAllWorkCategory] 모든 운동 카테고리 추가
 * [deleteAllWorkCategory] 모든 운동 카테고리 삭제하기
 * [getAllWorkCategory] 운동 카테고리 불러오기
 * @since 2023-10-15 18:32:57
 */
interface WorkCategoryDataSource {


    suspend fun insertAllWorkCategory(workCategory: List<WorkCategory>)
    suspend fun deleteAllWorkCategory()
    suspend fun getAllWorkCategory(): Flow<List<WorkCategory>>

    suspend fun fetch(workCategory: List<WorkCategory>)

}