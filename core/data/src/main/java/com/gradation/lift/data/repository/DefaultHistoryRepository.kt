package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.HistoryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHistoryRepository@Inject constructor(
    private val historyDataSource: HistoryDataSource,
) : HistoryRepository {
    override fun getHistory(): Flow<DataState<List<History>>> = flow{
       historyDataSource.getHistory().collect{ result ->
           when(result){
               is APIResult.Fail -> emit(DataState.Fail(result.message))
               is APIResult.Success -> emit(DataState.Success(result.data))
           }
       }
    }

    override fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<DataState<List<History>>>  = flow{
        historyDataSource.getHistoryByHistoryId(historyIdList).collect{ result ->
            when(result){
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun createHistory(createHistory: CreateHistory): Flow<DataState<Boolean>>  = flow{
        historyDataSource.createHistory(createHistory).collect{ result ->
            when(result){
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

    override fun deleteHistory(historyId: Int): Flow<DataState<Boolean>> = flow{
        historyDataSource.deleteHistory(historyId
        ).collect{ result ->
            when(result){
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }
}