package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.HistoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultHistoryDataSource @Inject constructor(
    private val historyService: HistoryService,
    private val NetworkResultHandler: NetworkResultHandler,
) : HistoryDataSource {
    override suspend fun getHistory(): Flow<NetworkResult<List<History>>> = flow {
        NetworkResultHandler {
            historyService.getHistory()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>> =
        flow {
            NetworkResultHandler {
                historyService.getHistoryByHistoryId(historyIdList.joinToString(","))
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<NetworkResult<Boolean>> =
        flow {
            NetworkResultHandler {
                historyService.createHistory(
                    createHistory.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    override suspend fun deleteHistory(historyId: Int): Flow<NetworkResult<Boolean>> = flow {
        NetworkResultHandler {
            historyService.deleteHistory(historyId)
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }

}