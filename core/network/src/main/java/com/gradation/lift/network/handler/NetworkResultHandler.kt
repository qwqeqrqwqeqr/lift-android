package com.gradation.lift.network.handler

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.AuthAPIResult
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.flow.Flow

interface NetworkResultHandler {
    suspend fun <T : Any> executeAuth(call: suspend () -> APIResultWrapper<T>): Flow<AuthAPIResult<T>>

    suspend fun <T : Any> executeDefault(call: suspend () -> APIResultWrapper<T>): Flow<DefaultAPIResult<T>>
}



