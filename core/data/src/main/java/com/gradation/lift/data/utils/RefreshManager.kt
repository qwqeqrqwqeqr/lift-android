package com.gradation.lift.data.utils

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.RefreshResult
import com.gradation.lift.network.common.toMessage
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface RefreshManager {
    suspend operator fun <T : Any> invoke(call: suspend (Token) -> Flow<APIResult<T>>): DataState<T>
}

class DefaultRefreshManager @Inject constructor(
    private val refreshService: RefreshService,
    private val userDataStoreDataSource: UserDataStoreDataSource,
) : RefreshManager {
    override suspend operator fun <T : Any> invoke(call: suspend (Token) -> Flow<APIResult<T>>): DataState<T> {
        return when (val refresh =
            refreshService.refresh(authorization = userDataStoreDataSource.refreshToken.first())) {
            is RefreshResult.Success -> {
                userDataStoreDataSource.setAccessToken(refresh.accessToken)
                when (val result = call.invoke(Token(
                    accessToken = userDataStoreDataSource.accessToken.first(),
                    refreshToken = userDataStoreDataSource.refreshToken.first()
                )).first()) {
                    is APIResult.Fail -> DataState.Fail(result.message)
                    is APIResult.Success -> DataState.Success(result.data)
                    is APIResult.Refresh -> {
                        userDataStoreDataSource.clearAll()
                        DataState.Fail("토큰 재발급 실패")
                    }
                }
            }
            RefreshResult.Fail -> {
                userDataStoreDataSource.clearAll()
                DataState.Fail("토큰 재발급 실패")
            }
        }
    }
}