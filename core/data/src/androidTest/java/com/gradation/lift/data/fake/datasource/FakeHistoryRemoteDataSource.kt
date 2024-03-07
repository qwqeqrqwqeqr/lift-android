package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.History.GetHistory.GET_HISTORY_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.History.GetHistoryByHistoryID.GET_HISTORY_BY_HISTORY_ID_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.History.UpdateHistoryInfo.UPDATE_HISTORY_INFO_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHistoryRemoteDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    HistoryRemoteDataSource {
    override suspend fun getHistory(): Flow<NetworkResult<List<History>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_HISTORY_RESPONSE_DTO.toDomain()))
        }
    }


    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = GET_HISTORY_BY_HISTORY_ID_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun deleteHistory(historyId: Int): Flow<NetworkResult<Unit>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
        }
    }

    override suspend fun updateHistoryInfo(updateHistoryInfo: UpdateHistoryInfo): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = UPDATE_HISTORY_INFO_RESPONSE_DTO.result))
        }
    }
}
