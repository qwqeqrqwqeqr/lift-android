package com.gradation.lift.database.datasource.workCategory

import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow

/**
 * [getAllWorkCategory] 운동 카테고리 불러오기
 * [fetch] 패치
 * @since 2024-03-06 17:05:59
 */
interface WorkCategoryDataSource {

    fun getAllWorkCategory(): Flow<List<WorkCategory>>

    suspend fun fetch(workCategory: List<WorkCategory>)

}