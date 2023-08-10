package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.DefaultSignInInfo
import com.gradation.lift.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signInDefault(signInInfo: DefaultSignInInfo) : Flow<APIResult<Token>>
    fun signUpDefault(signUpInfo: DefaultSignUpInfo) : Flow<APIResult<Boolean>>
}