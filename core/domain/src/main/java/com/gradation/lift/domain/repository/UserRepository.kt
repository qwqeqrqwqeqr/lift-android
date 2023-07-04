package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.user.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {

     fun getUserDetail(): Flow<DataState<UserDetail>>

     fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

     fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

     fun existUserDetail(): Flow<DataState<Boolean>>
}