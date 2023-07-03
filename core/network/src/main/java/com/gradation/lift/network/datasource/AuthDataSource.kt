package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Email
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signIn(account: Account) : Flow<DefaultAPIResult<Token>>

    fun signUp(signUpInfo: SignUpInfo) : Flow<DefaultAPIResult<Boolean>>
    fun checkDuplicateEmail(email: Email) : Flow<DefaultAPIResult<Boolean>>

}