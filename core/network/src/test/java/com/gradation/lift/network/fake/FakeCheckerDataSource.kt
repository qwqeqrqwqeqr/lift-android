package com.gradation.lift.network.fake

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.checkDuplicateEmailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Checker.checkDuplicateNameResponseDto
import com.gradation.lift.network.datasource.CheckerDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCheckerDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    CheckerDataSource {

    override suspend fun checkDuplicateEmail(email: String): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = checkDuplicateEmailResponseDto.result))
        }
    }

    override suspend fun checkDuplicateName(name: String): Flow<APIResult<Boolean>>  = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = checkDuplicateNameResponseDto.result))
        }
    }

}