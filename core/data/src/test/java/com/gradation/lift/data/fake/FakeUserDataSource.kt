package com.gradation.lift.data.fake

import com.gradation.lift.data.data.TestDtoDataGenerator.User.existUserDetailResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.User.getUserDetailResponseDto
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.data.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    UserDataSource {

    override suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = getUserDetailResponseDto.toDomain()))
        }
    }



    override suspend fun createUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Unit>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
        }
    }

    override suspend fun updateUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Unit>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data =Unit))
        }
    }

    override suspend fun existUserDetail(): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = existUserDetailResponseDto.result))
        }
    }



}