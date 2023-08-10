package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource.Companion.EMPTY_VALUE
import com.gradation.lift.network.common.toMessage
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : AuthRepository {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signInDefault(signInInfo).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> {
                    tokenDataStoreDataSource.setAccessToken(result.data.accessToken)
                    tokenDataStoreDataSource.setRefreshToken(result.data.refreshToken)
                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signUpDefault(signUpInfo).collect { result ->
            when (result) {
                is APIResult.Fail -> emit(DataState.Fail(result.message))
                is APIResult.Success -> {
                    emit(DataState.Success(true))
                }
            }
        }
    }


    override fun isSigned(): Flow<DataState<Boolean>> = flow {
        val condition = tokenDataStoreDataSource.accessToken.first() == EMPTY_VALUE ||
                tokenDataStoreDataSource.refreshToken.first() == EMPTY_VALUE
        if (condition) emit(DataState.Success(false)) else emit(DataState.Success(true))
    }

    override fun signOut(): Flow<DataState<Unit>> = flow {
        try {
            tokenDataStoreDataSource.clearAll()
            emit(DataState.Success(Unit))
        } catch (error: Exception) {
            emit(DataState.Fail(error.toMessage()))
        }
    }
}