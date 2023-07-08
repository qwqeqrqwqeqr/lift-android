package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signIn(signInInfo: SignInInfo) : Flow<APIResult<Token>>
    fun signUp(signUpInfo: SignUpInfo) : Flow<APIResult<Boolean>>
}