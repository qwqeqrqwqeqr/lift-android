package com.gradation.lift.data.repository

import android.util.Log
import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.auth.Account
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dataStoreDataSource: DataStoreDataSource,
) : AuthRepository {
    override fun signIn(account: Account): Flow<DataState<Boolean>> = flow {
        authDataSource.signIn(account).collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DataState.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DataState.Error(result.exception.toString()))
                is DefaultAPIResult.Loading -> emit(DataState.Loading)
                is DefaultAPIResult.Success -> {

                    dataStoreDataSource.setAccessToken(result.data.accessToken)
                    dataStoreDataSource.setRefreshToken(result.data.refreshToken)

                    Log.d("test",result.data.accessToken)
                    Log.d("test",result.data.refreshToken)
           


                    emit(DataState.Success(true))
                }
            }
        }
    }
}