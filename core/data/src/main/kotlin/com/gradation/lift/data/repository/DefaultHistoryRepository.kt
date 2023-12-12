package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.history.HistoryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHistoryRepository@Inject constructor(
    private val historyDataSource: HistoryDataSource,
) : HistoryRepository {
    override fun getHistory(): Flow<DataState<List<History>>> = flow{
       historyDataSource.getHistory().collect{ result ->
           when(result){
               is NetworkResult.Fail -> emit(DataState.Fail(result.message))
               is NetworkResult.Success -> emit(DataState.Success(result.data))
           }
       }
    }

    override fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<DataState<List<History>>>  = flow{
        historyDataSource.getHistoryByHistoryId(historyIdList).collect{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun createHistory(createHistory: CreateHistory): Flow<DataState<Unit>>  = flow{
        historyDataSource.createHistory(createHistory).collect{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun deleteHistory(historyId: Int): Flow<DataState<Unit>> = flow{
        historyDataSource.deleteHistory(historyId
        ).collect{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }
}