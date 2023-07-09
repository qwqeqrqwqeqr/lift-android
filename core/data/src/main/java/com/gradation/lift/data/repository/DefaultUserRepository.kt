package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.UserDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultUserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : UserRepository {
    override fun getUserDetail(): Flow<DataState<UserDetail>> = flow {

        userDataSource.getUserDetail(Token(accessToken = tokenDataStoreDataSource.accessToken.first()))
            .collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(DataState.Fail(result.message))
                    is APIResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }

    override fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.createUserDetail(
            Token(accessToken = tokenDataStoreDataSource.accessToken.first()),
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }


    override fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.updateUserDetail(
            Token(accessToken = tokenDataStoreDataSource.accessToken.first()),
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }

    override fun existUserDetail(): Flow<DataState<Boolean>> = flow {
        userDataSource.existUserDetail(
            Token(accessToken = tokenDataStoreDataSource.accessToken.first())
        ).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

}