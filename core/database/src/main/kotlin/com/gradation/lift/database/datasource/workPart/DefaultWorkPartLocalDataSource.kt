package com.gradation.lift.database.datasource.workPart

import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWorkPartLocalDataSource @Inject constructor(
    private val workPartDao: WorkPartDao,
) : WorkPartLocalDataSource {


    override fun getAllWorkPart(): Flow<List<WorkPart>> = flow {
        workPartDao.getAllWorkPart().collect { workPartEntity ->
            emit(workPartEntity.map { it.toDomain() })
        }
    }

    override suspend fun fetch(workPart: List<WorkPart>) {
        workPartDao.deleteAllWorkPart()
        workPartDao.insertAllWorkPart(
            *workPart.map { it.toEntity() }.toTypedArray()
        )
    }

}