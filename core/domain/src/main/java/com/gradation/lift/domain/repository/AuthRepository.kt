package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Boolean>>
    fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>>
    fun signInKakao(): Flow<DataState<Boolean>>
    fun signInNaver(): Flow<DataState<Boolean>>
    fun isSigned(): Flow<DataState<Boolean>>
    fun signOut(): Flow<DataState<Unit>>
}