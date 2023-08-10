package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.UserDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultUserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUserDetail(): Flow<DataState<UserDetail>> = flow {

        userDataSource.getUserDetail()
            .collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
    }

    override fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.createUserDetail(
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }


    override fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>> = flow {
        userDataSource.updateUserDetail(
            userDetail = userDetail
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }

    }

    override fun existUserDetail(): Flow<DataState<Boolean>> = flow {
        userDataSource.existUserDetail(
        ).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

}