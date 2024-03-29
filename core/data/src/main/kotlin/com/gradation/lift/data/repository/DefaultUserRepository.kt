package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultUserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : UserRepository {


    override fun getUserDetail(): Flow<DataState<UserDetail>> = flow {

        userRemoteDataSource.getUserDetail()
            .distinctUntilChanged().collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }.flowOn(dispatcherProvider.default)

    override fun getUserAuthenticationMethod(): Flow<DataState<LoginMethod>> = flow {
        userRemoteDataSource.getUserAuthenticationMethod()
            .distinctUntilChanged().collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }.flowOn(dispatcherProvider.default)

    override fun createUserDetail(userDetail: UserDetail): Flow<DataState<Unit>> = flow {
        userRemoteDataSource.createUserDetail(
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }.flowOn(dispatcherProvider.default)


    override fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Unit>> = flow {
        userRemoteDataSource.updateUserDetail(
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }.flowOn(dispatcherProvider.default)

    override fun updateUserDetailProfilePicture(userDetailProfilePicture: UserDetailProfilePicture): Flow<DataState<Unit>> =
        flow {
            userRemoteDataSource.updateUserDetailProfilePicture(
                userDetailProfilePicture
            ).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun updateUserDetailName(userDetailName: UserDetailName): Flow<DataState<Boolean>> =
        flow {
            userRemoteDataSource.updateUserDetailName(
                userDetailName
            ).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(
                        data =result.data,
                        message = result.message))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun updateUserDetailInfo(userDetailInfo: UserDetailInfo): Flow<DataState<Boolean>> =
        flow {
            userRemoteDataSource.updateUserDetailInfo(
                userDetailInfo
            ).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)


    override fun existUserDetail(): Flow<DataState<Boolean>> = flow {
        userRemoteDataSource.existUserDetail(
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)


    override fun deleteUser(deleteUserInfo: DeleteUserInfo): Flow<DataState<Unit>> = flow {
        userRemoteDataSource.deleteUser(deleteUserInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

}