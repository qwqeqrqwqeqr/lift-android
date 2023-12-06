package com.gradation.lift.network.datasource.auth

import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [AuthDataSource]
 * 인증 인가와 관련된 데이터 소스
 * @since 2023-08-28 22:06:44
 */
interface AuthDataSource {

    fun signInDefault(signInInfo: DefaultSignInInfo) : Flow<NetworkResult<Token>>
    fun signUpDefault(signUpInfo: DefaultSignUpInfo) : Flow<NetworkResult<Boolean>>
    fun signInKakao(signInInfo: KakaoSignInInfo) : Flow<NetworkResult<Token>>
    fun signInNaver(signInInfo: NaverSignInInfo) : Flow<NetworkResult<Token>>

}