package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.data.utils.toMessage
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.datasource.UserDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultUserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDataStoreDataSource: UserDataStoreDataSource,
    private val refreshManager: RefreshManager,
) : UserRepository {
    override suspend fun getUserDetail(): Flow<DataState<UserDetail>> = flow {

        userDataSource.getUserDetail(Token(accessToken = userDataStoreDataSource.accessToken.first()))
            .collect { result ->
                when (result) {
                    is AuthAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    AuthAPIResult.Refresh -> emit(refreshManager {
                        userDataSource.getUserDetail(
                            Token()
                        )
                    })
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }

    override suspend fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.createUserDetail(
            Token(accessToken = userDataStoreDataSource.accessToken.first()),
            userDetail = userDetail
        ).collect { result ->
                when (result) {
                    is AuthAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    AuthAPIResult.Refresh -> emit(refreshManager {
                        userDataSource.createUserDetail(
                            Token(), userDetail = userDetail
                        )
                    })
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                }
            }

    }


    override suspend fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.updateUserDetail(
            Token(accessToken = userDataStoreDataSource.accessToken.first()),
            userDetail = userDetail
        ).collect { result ->
                when (result) {
                    is AuthAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    AuthAPIResult.Refresh -> emit(refreshManager {
                        userDataSource.updateUserDetail(
                            Token(), userDetail = userDetail
                        )
                    })
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                }
            }

    }

    override suspend fun existUserDetail(): Flow<DataState<Boolean>> = flow {
        userDataSource.existUserDetail(
            Token(accessToken = userDataStoreDataSource.accessToken.first())
        ).collect { result ->
                when (result) {
                    is AuthAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                    is AuthAPIResult.Fail -> emit(DataState.Fail(result.message))
                    AuthAPIResult.Refresh -> emit(refreshManager {
                        userDataSource.existUserDetail(Token())
                    })
                    is AuthAPIResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }

}