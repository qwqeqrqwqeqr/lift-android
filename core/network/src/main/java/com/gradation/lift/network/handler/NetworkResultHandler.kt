package com.gradation.lift.network.handler

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NetworkResultHandler {
    suspend operator fun <T : Any> invoke(call: suspend () -> Response<APIResultWrapper<T>>): Flow<NetworkResult<T>>
}



