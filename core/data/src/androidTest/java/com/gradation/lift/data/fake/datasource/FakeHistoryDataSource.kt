package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.History.getHistoryByHistoryIdResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.History.getHistoryResponseDto
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHistoryDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    HistoryDataSource {
    override suspend fun getHistory(): Flow<NetworkResult<List<History>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getHistoryResponseDto.toDomain()))
        }
    }


    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getHistoryByHistoryIdResponseDto.toDomain()))
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
            TestReturnState.Success -> emit(NetworkResult.Success(data = FAKE_BOOLEAN_DATA))
        }
    }
}
