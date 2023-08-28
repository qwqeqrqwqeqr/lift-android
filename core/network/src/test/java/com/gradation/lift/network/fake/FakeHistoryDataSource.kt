package com.gradation.lift.network.fake

import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryByHistoryIdResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryResponseDto
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHistoryDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    HistoryDataSource {
    override suspend fun getHistory(): Flow<NetworkResult<List<History>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getHistoryResponseDto.toDomain()))
        }
    }


    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<NetworkResult<List<History>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
                TestReturnState.Success -> emit(NetworkResult.Success(data = getHistoryByHistoryIdResponseDto.toDomain()))
            }
        }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun deleteHistory(historyId: Int): Flow<NetworkResult<Unit>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
        }
    }
}
