package com.gradation.lift.network.fake

import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestCheckerDataGenerator.checkDuplicateEmailResponseDto
import com.gradation.lift.network.data.TestCheckerDataGenerator.checkDuplicateNameResponseDto
import com.gradation.lift.network.data.TestUserDataGenerator.createUserDetailResponseDto
import com.gradation.lift.network.data.TestUserDataGenerator.existUserDetailResponseDto
import com.gradation.lift.network.data.TestUserDataGenerator.getUserDetailResponseDto
import com.gradation.lift.network.data.TestUserDataGenerator.updateUserDetailResponseDto
import com.gradation.lift.network.data.TestWorkDataGenerator.getWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.data.TestWorkDataGenerator.getWorkCategoryResponseDto
import com.gradation.lift.network.data.TestWorkDataGenerator.getWorkPartResponseDto
import com.gradation.lift.network.datasource.CheckerDataSource
import com.gradation.lift.network.datasource.UserDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.dto.user.UpdateUserDetailResponseDto
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCheckerDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    CheckerDataSource {

    override suspend fun checkDuplicateEmail(email: Email): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = checkDuplicateEmailResponseDto.result))
        }
    }

    override suspend fun checkDuplicateName(name: Name): Flow<APIResult<Boolean>>  = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = checkDuplicateNameResponseDto.result))
        }
    }

}