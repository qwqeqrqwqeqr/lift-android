package com.gradation.lift.network.datasource.history

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.HistoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultHistoryRemoteDataSource @Inject constructor(
    private val historyService: HistoryService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : HistoryRemoteDataSource {
    override suspend fun getHistory(): Flow<NetworkResult<List<History>>> = flow {
        networkResultHandler {
            historyService.getHistory()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>> =
        flow {
            networkResultHandler {
                historyService.getHistoryByHistoryId(historyIdList.joinToString(","))
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun createHistory(createHistory: CreateHistory): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                historyService.createHistory(
                    createHistory.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun deleteHistory(historyId: Int): Flow<NetworkResult<Unit>> = flow {
        networkResultHandler {
            historyService.deleteHistory(historyId)
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun updateHistoryInfo(updateHistoryInfo: UpdateHistoryInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                historyService.updateHistoryInfo(updateHistoryInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))

                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)

}