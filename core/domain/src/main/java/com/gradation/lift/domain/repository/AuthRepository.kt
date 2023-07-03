package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.auth.SignInInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(account: SignInInfo): Flow<DataState<Boolean>>
    fun isSigned(): Flow<DataState<Boolean>>
    fun signOut(): Flow<DataState<Unit>>
}