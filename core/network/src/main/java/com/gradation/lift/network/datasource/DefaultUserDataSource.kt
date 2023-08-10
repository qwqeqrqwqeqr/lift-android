package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailRequestDto
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userService: UserService,
    private val networkResultHandler: NetworkResultHandler,
) : UserDataSource {

    override suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>> = flow {
        networkResultHandler {
            userService.getUserDetail()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override suspend fun createUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Boolean>> = flow {
        networkResultHandler {
            userService.createUserDetail(
                createUserDetailRequestDto = CreateUserDetailRequestDto(
                    userDetailDto = userDetail.toDto()
                )
            )
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }

    override suspend fun updateUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Boolean>> = flow {
        networkResultHandler {
            userService.updateUserDetail(
                updateUserDetailRequestDto = UpdateUserDetailRequestDto(
                    userDetailDto = userDetail.toDto()
                )
            )
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }

    override suspend fun existUserDetail(): Flow<NetworkResult<Boolean>> = flow {
        networkResultHandler {
            userService.existUserDetail()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }

}

