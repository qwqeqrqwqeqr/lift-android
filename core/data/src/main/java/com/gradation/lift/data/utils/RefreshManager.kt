package com.gradation.lift.data.utils

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.RefreshResult
import com.gradation.lift.network.service.RefreshService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface RefreshManager {
    suspend operator fun <T : Any> invoke(call: suspend () -> Flow<APIResult<T>>): DataState<T>
}

class DefaultRefreshManager @Inject constructor(
    private val refreshService: RefreshService,
    private val dataStoreDataSource: DataStoreDataSource,
) : RefreshManager {
    override suspend operator fun <T : Any> invoke(call: suspend () -> Flow<APIResult<T>>): DataState<T> {
        return when (val refresh =
            refreshService.refresh(refreshToken = dataStoreDataSource.refreshToken.first())) {
            is RefreshResult.Success -> {
                dataStoreDataSource.setAccessToken(refresh.accessToken)
                when (val result = call.invoke().first()) {
                    is APIResult.Fail -> DataState.Fail(result.message)
                    is APIResult.Error -> DataState.Error(result.exception.toString())
                    is APIResult.Loading -> DataState.Loading
                    is APIResult.Success -> DataState.Success(result.data)
                    is APIResult.Refresh -> DataState.Error("토큰 재발급 오류")
                }
            }
            RefreshResult.Fail -> DataState.Error("토큰 재발급 오류")
        }
    }
}