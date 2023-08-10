package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CheckerDataSource {
    suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>>
    suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>>
}