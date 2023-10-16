package com.gradation.lift.database.datasource.routine

import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultRoutineDataSource @Inject constructor(
    private val routineDao: RoutineDao,
) : RoutineDataSource {
    override suspend fun getAllRoutineSetRoutine(): Flow<List<RoutineSetRoutine>> = flow {
        routineDao.getAllRoutineSetRoutine().collect { routineSetRoutineEntity ->
            emit(
                routineSetRoutineEntity.map {
                    it.key.toDomain()
                        .copy(routine = it.value.map { routineEntity -> routineEntity.toDomain() })
                }
            )
        }
    }

    override suspend fun deleteAllRoutineSetRoutine() {
        routineDao.deleteAllRoutineSetRoutine()
    }

    override suspend fun insertAllRoutine(
        routineSetRoutine: List<RoutineSetRoutine>,
    ) {
        routineDao.insertAll(
            routineSetRoutineEntity = routineSetRoutine.map { it.toEntity() },
            routineEntity = routineSetRoutine.flatMap { it.routine.map { it.toEntity() } }
        )
    }

    override suspend fun fetch(routineSetRoutine: List<RoutineSetRoutine>) {
        routineDao.deleteAllRoutineSetRoutine()
        routineDao.insertAll(
            routineSetRoutineEntity = routineSetRoutine.map { it.toEntity() },
            routineEntity = routineSetRoutine.flatMap { it.routine.map { it.toEntity() } }
        )
    }


}