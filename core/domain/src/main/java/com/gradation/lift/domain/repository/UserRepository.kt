package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.user.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserDetail(): Flow<DataState<UserDetail>>

    suspend fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

    suspend fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

    suspend fun existUserDetail(): Flow<DataState<Boolean>>
}