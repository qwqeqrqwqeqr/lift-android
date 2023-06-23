package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signIn(account: Account) : Flow<DefaultAPIResult<Token>>

}