package com.gradation.lift.network.datasource

import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface CheckerDataSource {
    suspend fun checkDuplicateEmail(email: String): Flow<APIResult<Boolean>>

    suspend fun checkDuplicateName(name: String): Flow<APIResult<Boolean>>
}