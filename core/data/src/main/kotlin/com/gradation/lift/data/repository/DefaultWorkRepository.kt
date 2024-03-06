package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDao: WorkDao,
    private val dispatcherProvider: DispatcherProvider,
) : WorkRepository {

    override fun getWork(): Flow<DataState<Work>> = flow {
        workDao.getAllWork().collect {
            if (it.keys.size != 1) {
                emit(DataState.Fail("운동 불러오기를 실패하였습니다."))
            } else {
                with(it.entries.first()) {
                    emit(DataState.Success(
                        this.key.toDomain().let { work ->
                            work.copy(routine = this.value.map { workRoutineEntity -> workRoutineEntity.toDomain() })
                        }
                    ))
                }
            }
        }
    }.catch {
        emit(DataState.Fail("운동 불러오기를 실패하였습니다."))
    }.flowOn(dispatcherProvider.default)

    override fun createWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.insert(
            workEntity = work.toEntity(),
            workRoutineEntity = work.routine.map { it.toEntity() }
        )
        emit(DataState.Success(Unit))
    }.flowOn(dispatcherProvider.default)


    override fun updateWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.updateWork(work.toEntity())


        emit(DataState.Success(Unit))
    }.flowOn(dispatcherProvider.default)


    override fun deleteWork(work: Work): Flow<DataState<Unit>> = flow {
        emit(DataState.Success(Unit))
    }.flowOn(dispatcherProvider.default)

    override fun deleteAllWork(): Flow<DataState<Unit>> = flow {
        workDao.deleteAllWork()
        emit(DataState.Success(Unit))
    }.flowOn(dispatcherProvider.default)


    override fun existWork(): Flow<DataState<Boolean>> = flow {
        workDao.existWork().map { emit(DataState.Success(it)) }
    }.flowOn(dispatcherProvider.default)
}

