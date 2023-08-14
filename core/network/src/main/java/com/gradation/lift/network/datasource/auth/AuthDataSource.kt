package com.gradation.lift.network.datasource.auth

import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signInDefault(signInInfo: DefaultSignInInfo) : Flow<NetworkResult<Token>>
    fun signUpDefault(signUpInfo: DefaultSignUpInfo) : Flow<NetworkResult<Boolean>>
    fun signInKakao(signInInfo: KakaoSignInInfo) : Flow<NetworkResult<Token>>
    fun signInNaver(signInInfo: NaverSignInInfo) : Flow<NetworkResult<Token>>

}