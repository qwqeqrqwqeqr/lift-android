package com.gradation.lift.network.handler

import com.gradation.lift.common.dispatcher.DispatcherProvider
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.APIResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultNetworkResultHandler @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : NetworkResultHandler {
    override suspend fun <T : Any> invoke(call: suspend () -> APIResultWrapper<T>): Flow<APIResult<T>> {
        TODO("Not yet implemented")
    }
}