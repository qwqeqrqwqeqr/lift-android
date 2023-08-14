package com.gradation.lift.network.datasource.user

import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>>

    suspend fun createUserDetail(userDetail: UserDetail): Flow<NetworkResult<Boolean>>

    suspend fun updateUserDetail(userDetail: UserDetail): Flow<NetworkResult<Boolean>>

    suspend fun existUserDetail(): Flow<NetworkResult<Boolean>>
}