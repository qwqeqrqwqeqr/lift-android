package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun signIn(account: Account) : Flow<APIResult<Token>>
    suspend fun refresh() : Flow<APIResult<Token>>

}