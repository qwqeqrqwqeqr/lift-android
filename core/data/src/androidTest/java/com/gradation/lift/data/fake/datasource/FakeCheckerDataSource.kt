package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Checker.CheckDuplicateEmail.CHECK_DUPLICATE_EMAIL_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Checker.CheckDuplicateName.CHECK_DUPLICATE_NAME_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCheckerDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    CheckerDataSource {

    override suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = CHECK_DUPLICATE_EMAIL_RESPONSE_DTO.result))
        }
    }

    override suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>>  = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = CHECK_DUPLICATE_NAME_RESPONSE_DTO.result))
        }
    }

}