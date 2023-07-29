package com.gradation.lift.network.fake

import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.History
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.History.createHistoryResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.deleteHistoryResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryByHistoryIdResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.History.getHistoryResponseDto
import com.gradation.lift.network.datasource.HistoryDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHistoryDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    HistoryDataSource {
    override suspend fun getHistory(): Flow<APIResult<List<History>>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getHistoryResponseDto.toDomain()))
        }
    }


    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<APIResult<List<History>>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(APIResult.Fail("오류"))
                TestReturnState.Success -> emit(APIResult.Success(data = getHistoryByHistoryIdResponseDto.toDomain()))
            }
        }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<APIResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(APIResult.Fail("오류"))
                TestReturnState.Success -> emit(APIResult.Success(data = createHistoryResponseDto.result))
            }
        }

    override suspend fun deleteHistory(historyId: Int): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = deleteHistoryResponseDto.result))
        }
    }
}
