package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.HistoryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DefaultHistoryRepository@Inject constructor(
    private val historyDataSource: HistoryDataSource,
) : HistoryRepository {
    override fun getHistory(): Flow<DataState<List<History>>> = flow{
       historyDataSource.getHistory().transform{ result ->
           when(result){
               is NetworkResult.Fail -> emit(DataState.Fail(result.message))
               is NetworkResult.Success -> emit(DataState.Success(result.data))
           }
       }
    }

    override fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<DataState<List<History>>>  = flow{
        historyDataSource.getHistoryByHistoryId(historyIdList).transform{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun createHistory(createHistory: CreateHistory): Flow<DataState<Boolean>>  = flow{
        historyDataSource.createHistory(createHistory).transform{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun deleteHistory(historyId: Int): Flow<DataState<Boolean>> = flow{
        historyDataSource.deleteHistory(historyId
        ).transform{ result ->
            when(result){
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }
}