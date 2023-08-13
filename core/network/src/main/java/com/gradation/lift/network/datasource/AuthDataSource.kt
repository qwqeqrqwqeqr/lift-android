package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signInDefault(signInInfo: DefaultSignInInfo) : Flow<NetworkResult<Token>>
    fun signUpDefault(signUpInfo: DefaultSignUpInfo) : Flow<NetworkResult<Boolean>>
    fun signInFromKakao() : Flow<NetworkResult<String>>

    suspend fun signInKakao(signInInfo: KakaoSignInInfo) : Flow<NetworkResult<Token>>

}