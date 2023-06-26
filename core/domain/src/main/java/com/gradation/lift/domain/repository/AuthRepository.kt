package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(account: Account): Flow<DataState<Boolean>>
    fun isSignedIn(): Flow<DataState<Boolean>>

    fun signOut(): Flow<DataState<Boolean>>
}