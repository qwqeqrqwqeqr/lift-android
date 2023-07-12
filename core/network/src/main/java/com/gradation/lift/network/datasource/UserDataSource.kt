package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUserDetail(): Flow<APIResult<UserDetail>>

    suspend fun createUserDetail(userDetail: UserDetail): Flow<APIResult<Boolean>>

    suspend fun updateUserDetail(userDetail: UserDetail): Flow<APIResult<Boolean>>

    suspend fun existUserDetail(): Flow<APIResult<Boolean>>
}