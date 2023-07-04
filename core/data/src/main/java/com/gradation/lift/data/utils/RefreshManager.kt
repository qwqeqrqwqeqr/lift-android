package com.gradation.lift.data.utils

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.RefreshResult
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface RefreshManager {
    suspend operator fun <T : Any> invoke(call: suspend (Token) -> Flow<AuthAPIResult<T>>): DataState<T>
}

class DefaultRefreshManager @Inject constructor(
    private val refreshService: RefreshService,
    private val userDataStoreDataSource: UserDataStoreDataSource,
) : RefreshManager {
    override suspend operator fun <T : Any> invoke(call: suspend (Token) -> Flow<AuthAPIResult<T>>): DataState<T> {
        return when (val refresh =
            refreshService.refresh(refreshToken = userDataStoreDataSource.refreshToken.first())) {
            is RefreshResult.Success -> {
                userDataStoreDataSource.setAccessToken(refresh.accessToken)
                when (val result = call.invoke(Token(
                    accessToken = userDataStoreDataSource.accessToken.first(),
                    refreshToken = userDataStoreDataSource.refreshToken.first()
                )).first()) {
                    is AuthAPIResult.Fail -> DataState.Fail(result.message)
                    is AuthAPIResult.Error -> DataState.Error(result.throwable.toMessage())
                    is AuthAPIResult.Success -> DataState.Success(result.data)
                    is AuthAPIResult.Refresh -> {
                        userDataStoreDataSource.clearAll()
                        DataState.Error(Throwable().toMessage())
                    }
                }
            }
            RefreshResult.Fail -> {
                userDataStoreDataSource.clearAll()
                DataState.Error(Throwable().toMessage())
            }
        }
    }
}