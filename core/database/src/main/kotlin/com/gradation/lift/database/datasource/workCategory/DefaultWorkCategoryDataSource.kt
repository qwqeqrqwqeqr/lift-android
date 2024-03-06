package com.gradation.lift.database.datasource.workCategory

import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWorkCategoryDataSource @Inject constructor(
    private val workCategoryDao: WorkCategoryDao,
) : WorkCategoryDataSource {

    override fun getAllWorkCategory(): Flow<List<WorkCategory>> = flow {
        workCategoryDao.getAllWorkCategory().collect { workCategoryEntity ->
            emit(workCategoryEntity.map { it.toDomain() })
        }
    }


    override suspend fun fetch(workCategory: List<WorkCategory>) {
        workCategoryDao.deleteAllWorkCategory()
        workCategoryDao.insertAllWorkCategory(
            *workCategory.map { it.toEntity() }.toTypedArray()
        )
    }

}