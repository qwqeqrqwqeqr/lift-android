package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.User.ExistUserDetail.EXIST_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.User.GetAuthenticationMethod.GET_USER_AUTHENTICATION_METHOD_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.User.GetUserDetail.GET_USER_DETAIL_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.User.UpdateUserDetailInfo.UPDATE_USER_DETAIL_INFO_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.User.UpdateUserDetailName.UPDATE_USER_DETAIL_NAME_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserRemoteDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    UserRemoteDataSource {
    override suspend fun deleteUser(deleteUserInfo: DeleteUserInfo): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_USER_DETAIL_RESPONSE_DTO.toDomain()))
        }
    }

    override suspend fun getUserAuthenticationMethod(): Flow<NetworkResult<LoginMethod>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = GET_USER_AUTHENTICATION_METHOD_RESPONSE_DTO.toDomain()))
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
            TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
        }
    }

    override suspend fun updateUserDetailProfilePicture(userDetailProfilePicture: UserDetailProfilePicture): Flow<NetworkResult<Unit>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = Unit))
            }
        }

    override suspend fun existUserDetail(): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = EXIST_USER_DETAIL_RESPONSE_DTO.result))
        }
    }

    override suspend fun updateUserDetailName(userDetailName: UserDetailName): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = UPDATE_USER_DETAIL_NAME_RESPONSE_DTO.result))
            }
        }

    override suspend fun updateUserDetailInfo(userDetailInfo: UserDetailInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = UPDATE_USER_DETAIL_INFO_RESPONSE_DTO.result))
            }
        }

}