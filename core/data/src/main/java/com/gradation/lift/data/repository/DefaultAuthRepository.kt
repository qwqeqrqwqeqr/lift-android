package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.network.common.toMessage
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource.Companion.EMPTY_VALUE
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDataStoreDataSource: UserDataStoreDataSource,
) : AuthRepository {
    override fun signIn(signInInfo: SignInInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signIn(signInInfo).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                is DefaultAPIResult.Success -> {

                    userDataStoreDataSource.setAccessToken(result.data.accessToken)
                    userDataStoreDataSource.setRefreshToken(result.data.refreshToken)
                    userDataStoreDataSource.setUserId(signInInfo.id)

                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun signUp(signUpInfo: SignUpInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signUp(signUpInfo).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DataState.Error(result.throwable.toMessage()))
                is DefaultAPIResult.Success -> {
                    emit(DataState.Success(true))
                }
            }
        }
    }


    override fun isSigned(): Flow<DataState<Boolean>> = flow {
        val condition = userDataStoreDataSource.accessToken.first() == EMPTY_VALUE ||
                userDataStoreDataSource.refreshToken.first() == EMPTY_VALUE
        if (condition) emit(DataState.Success(false)) else emit(DataState.Success(true))
    }

    override fun signOut(): Flow<DataState<Unit>> = flow {
        try {
            userDataStoreDataSource.clearAll()
            emit(DataState.Success(Unit))
        } catch (error: Exception) {
            emit(DataState.Error(error.toMessage()))
        }
    }
}