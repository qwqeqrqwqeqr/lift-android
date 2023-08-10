package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signInDefault(signInInfo: SignInInfo): Flow<DataState<Boolean>>
    fun signUpDefault(signUpInfo: SignUpInfo): Flow<DataState<Boolean>>
    fun isSigned(): Flow<DataState<Boolean>>
    fun signOut(): Flow<DataState<Unit>>
}