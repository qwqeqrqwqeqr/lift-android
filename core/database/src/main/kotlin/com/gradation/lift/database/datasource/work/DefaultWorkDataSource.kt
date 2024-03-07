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

    override fun load(): Flow<Work> = flow {
        workDao.getAllWork().collect { workEntity ->
            emit(
                workEntity.map {
                    it.key.toDomain()
                        .copy(routine = it.value.map { workRoutineEntity -> workRoutineEntity.toDomain() })
                }.first()
            )
        }
    }

    override fun existWork(): Flow<Boolean> = flow {
        workDao.existWork().collect { emit(it) }
    }

    override suspend fun fetch(work: Work) {
        workDao.deleteAllWork()

        workDao.insert(
            workEntity = work.toEntity(),
            workRoutineEntity = work.routine.map { it.toEntity() }
        )
    }

    override suspend fun clear() {
        workDao.deleteAllWork()
    }

}