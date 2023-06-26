package com.gradation.lift.data.repository

import android.util.Log
import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource.Companion.EMPTY_VALUE
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.Account
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDataStoreDataSource: UserDataStoreDataSource,
) : AuthRepository {
    override fun signIn(account: Account): Flow<DataState<Boolean>> = flow {
        authDataSource.signIn(account).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DataState.Error(result.throwable))
                is DefaultAPIResult.Success -> {

                    userDataStoreDataSource.setAccessToken(result.data.accessToken)
                    userDataStoreDataSource.setRefreshToken(result.data.refreshToken)

                    Log.d("test", result.data.accessToken)
                    Log.d("test", result.data.refreshToken)

                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun isSignedIn(): Flow<DataState<Boolean>> = flow {
        if (userDataStoreDataSource.accessToken.first() == EMPTY_VALUE ||
            userDataStoreDataSource.refreshToken.first() == EMPTY_VALUE ||
            userDataStoreDataSource.userId.first() == EMPTY_VALUE
        ) {
            emit(DataState.Fail("로그인 되어있지 않습니다."))
        }

        emit(DataState.Success(true))
    }

    override fun signOut(): Flow<DataState<Boolean>> = flow {
        try {
            userDataStoreDataSource.clearAll()
            emit(DataState.Success(true))
        }
        catch(error: Exception){
            emit(DataState.Error(error))
        }
    }
}