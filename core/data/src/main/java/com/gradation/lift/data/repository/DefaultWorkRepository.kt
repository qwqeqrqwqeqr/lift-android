package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.NopCollector.emit
import javax.inject.Inject


class DefaultWorkRepository @Inject constructor(
    private val workDataSource: WorkDataSource,
    private val workDao: WorkDao,
    private val workCategoryDao: WorkCategoryDao,

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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getWork(): Flow<DataState<Work>> = flow{
       workDao.getAllWork().flatMapLatest {
           if(it.keys.size!=1){
               emit(DataState.Fail("운동을 불러오는데 실패하였습니다."))
           }
           else{
           }
       }

    }

    fun updateWork(work: Work): Flow<DataState<Work>> {
        TODO("Not yet implemented")
    }

    override fun deleteWork(work: Work): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun existWork(): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }
}