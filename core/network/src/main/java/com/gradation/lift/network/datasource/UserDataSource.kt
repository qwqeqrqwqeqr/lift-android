package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Token
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.AuthAPIResult
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUserDetail(token: Token): Flow<AuthAPIResult<UserDetail>>

    suspend fun createUserDetail(token: Token,userDetail: UserDetail): Flow<AuthAPIResult<Boolean>>

    suspend fun updateUserDetail(token: Token,userDetail: UserDetail): Flow<AuthAPIResult<Boolean>>

    suspend fun existUserDetail(token: Token): Flow<AuthAPIResult<Boolean>>
}