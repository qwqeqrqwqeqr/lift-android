package com.gradation.lift.database.datasource.work

import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWorkDataSource @Inject constructor(
    private val workDao: WorkDao,
) : WorkDataSource {
    override suspend fun insertWork(work: Work) {
        workDao.insert(
            workEntity = work.toEntity(),
            workRoutineEntity = work.routine.map { it.toEntity() }
        )
    }

    override suspend fun updateWork(work: Work) {
        workDao.updateWork(work.toEntity())
    }

    override suspend fun deleteWork(work: Work) {
        workDao.deleteWork(work.toEntity())
    }


    override suspend fun deleteAllWork() {
        workDao.deleteAllWork()
    }

    override fun getAllWork(): Flow<List<Work>> = flow {
        workDao.getAllWork().collect { workEntity ->
            emit(workEntity.map {
                it.key.toDomain().copy(
                    routine = it.value.map { workRoutineEntity -> workRoutineEntity.toDomain() }
                )
            })
        }
    }

    override fun existWork(): Flow<Boolean> = flow {
        workDao.existWork().collect {
            emit(it)
        }
    }

}