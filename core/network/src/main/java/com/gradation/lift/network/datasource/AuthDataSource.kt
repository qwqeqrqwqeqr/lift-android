package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    suspend fun signIn() : Flow<APIResult<>>


    suspend fun refresh() : Flow<APIResult<>>


}