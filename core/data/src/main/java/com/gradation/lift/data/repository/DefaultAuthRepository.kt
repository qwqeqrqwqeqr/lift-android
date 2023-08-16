package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.mapper.toMessage
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : AuthRepository {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signInDefault(signInInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    tokenDataStoreDataSource.setAccessToken(result.data.accessToken)
                    tokenDataStoreDataSource.setRefreshToken(result.data.refreshToken)
                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Common())
                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signUpDefault(signUpInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    emit(DataState.Success(true))
                }
            }
        }
    }



    override fun isSigned(): Flow<DataState<Boolean>> = flow {
        val condition = tokenDataStoreDataSource.accessToken.first().isNotBlank() &&
                tokenDataStoreDataSource.refreshToken.first().isNotBlank()
        if (condition) emit(DataState.Success(true)) else emit(DataState.Success(false))
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