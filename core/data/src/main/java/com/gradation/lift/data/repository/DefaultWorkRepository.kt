package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val workDao: WorkDao,

    ) : WorkRepository {
    override fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        workDataSource.getWorkPart().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }


    override fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        workDataSource.getWorkCategory().collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun getWorkCategoryByWorkPart(workPart: String): Flow<DataState<List<WorkCategory>>> =
        flow {
            workDataSource.getWorkCategoryByWorkPart(workPart).collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(DataState.Fail(result.message))
                    is APIResult.Success -> emit(DataState.Success(result.data))
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

    override fun updateWork(work: Work): Flow<DataState<Unit>> = flow {
        workDao.updateWork(work.toEntity())
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

