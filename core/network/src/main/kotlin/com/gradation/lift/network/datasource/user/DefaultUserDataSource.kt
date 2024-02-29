package com.gradation.lift.network.datasource.user

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.dto.user.CreateUserDetailRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailNameRequestDto
import com.gradation.lift.network.dto.user.UpdateUserDetailRequestDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userService: UserService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider
) : UserDataSource {
    override suspend fun deleteUser(deleteUserInfo: DeleteUserInfo): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                userService.deleteUser(deleteUserInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }.flowOn(dispatcherProvider.default)


    override suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>> = flow {
        networkResultHandler {
            userService.getUserDetail()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun getUserAuthenticationMethod(): Flow<NetworkResult<LoginMethod>> = flow {
        networkResultHandler {
            userService.getUserAuthenticationMethod()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun createUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Unit>> = flow {
        networkResultHandler {
            userService.createUserDetail(
                createUserDetailRequestDto = CreateUserDetailRequestDto(
                    userDetailDto = userDetail.toDto()
                )
            )
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun updateUserDetail(
        userDetail: UserDetail,
    ): Flow<NetworkResult<Unit>> = flow {
        networkResultHandler {
            userService.updateUserDetail(
                updateUserDetailRequestDto = UpdateUserDetailRequestDto(
                    userDetailDto = userDetail.toDto()
                )
            )
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun updateUserDetailProfilePicture(userDetailProfilePicture: UserDetailProfilePicture): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                userService.updateUserDetailProfilePicture(userDetailProfilePicture.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun existUserDetail(): Flow<NetworkResult<Boolean>> = flow {
        networkResultHandler {
            userService.existUserDetail()
        }.collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override suspend fun updateUserDetailName(userDetailName: UserDetailName): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                userService.updateUserDetailName(UpdateUserDetailNameRequestDto(name = userDetailName.name))
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(
                        NetworkResult.Success(
                            data = result.data.result,
                            message = result.message
                        )
                    )
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun updateUserDetailInfo(userDetailInfo: UserDetailInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                userService.updateUserDetailInfo(userDetailInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)

}

