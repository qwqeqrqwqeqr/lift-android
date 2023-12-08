package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val workDao: WorkDao,

    ) : WorkRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        workDataSource.getWorkPart().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }


    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>> =
        flow {
            workDataSource.getWorkCategoryByWorkPart(workPart).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }

    override fun getPopularWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getPopularWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getRecommendWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getRecommendWorkCategory().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getWork(): Flow<DataState<Work>> = flow {
        workDao.getAllWork().map {
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
    }

    override fun createWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.insert(
            workEntity = work.toEntity(),
            workRoutineEntity = work.routine.map { it.toEntity() }
        )
    }


    override fun updateWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.updateWork(work.toEntity())

        work.routine.map {
            workDao.updateWorkRoutine(it.toEntity())
        }


        emit(DataState.Success(Unit))
    }


    override fun deleteWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.deleteWork(work.toEntity())
        emit(DataState.Success(Unit))
    }

    override fun existWork(): Flow<DataState<Boolean>> = flow {
        workDao.existWork().map { emit(DataState.Success(it)) }
    }
}

