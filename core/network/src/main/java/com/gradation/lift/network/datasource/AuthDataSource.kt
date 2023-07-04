package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signIn(signInInfo: SignInInfo) : Flow<DefaultAPIResult<Token>>
    fun signUp(signUpInfo: SignUpInfo) : Flow<DefaultAPIResult<Boolean>>
}