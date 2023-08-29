package com.gradation.lift.network.fake

import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.checkDuplicateEmailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.checkDuplicateNameResponseDto
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCheckerDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    CheckerDataSource {

    override suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = checkDuplicateEmailResponseDto.result))
        }
    }

    override suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>>  = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = checkDuplicateNameResponseDto.result))
        }
    }

}