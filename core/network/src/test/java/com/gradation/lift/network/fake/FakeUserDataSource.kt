package com.gradation.lift.network.fake

import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.User.createUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.existUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.getUserDetailResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.User.updateUserDetailResponseDto
import com.gradation.lift.network.datasource.UserDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) : UserDataSource {

    override suspend fun getUserDetail(): Flow<APIResult<UserDetail>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = getUserDetailResponseDto.toDomain()))
        }
    }



    override suspend fun createUserDetail(
        userDetail: UserDetail,
    ): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = createUserDetailResponseDto.result))
        }
    }

    override suspend fun updateUserDetail(
        userDetail: UserDetail,
    ): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data =updateUserDetailResponseDto.result))
        }
    }

    override suspend fun existUserDetail(): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = existUserDetailResponseDto.result))
        }
    }



}