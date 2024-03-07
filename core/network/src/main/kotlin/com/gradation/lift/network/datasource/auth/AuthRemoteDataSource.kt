package com.gradation.lift.network.datasource.auth

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import com.gradation.lift.model.model.auth.GoogleSignInInfo
import com.gradation.lift.model.model.auth.GoogleSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.KakaoSignUpInfo
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.NaverSignUpInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [AuthRemoteDataSource]
 * 인증 인가와 관련된 데이터 소스
 * @since 2023-08-28 22:06:44
 */
interface AuthRemoteDataSource {

    suspend fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>>
    suspend fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>>
    suspend fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>>
    suspend fun signUpKakao(signUpInfo: KakaoSignUpInfo): Flow<NetworkResult<Boolean>>
    suspend fun signInNaver(signInInfo: NaverSignInInfo): Flow<NetworkResult<Token>>
    suspend fun signUpNaver(signUpInfo: NaverSignUpInfo): Flow<NetworkResult<Boolean>>
    suspend fun signInGoogle(signInInfo: GoogleSignInInfo): Flow<NetworkResult<Token>>
    suspend fun signUpGoogle(signUpInfo: GoogleSignUpInfo): Flow<NetworkResult<Boolean>>
    suspend fun checkUserExist(userId: String, email: String): Flow<NetworkResult<Boolean>>
    suspend fun updateUserPassword(updatePasswordInfo: UpdatePasswordInfo): Flow<NetworkResult<Boolean>>
    suspend fun createEmailAuthenticationCode(emailAuthenticationInfo: EmailAuthenticationInfo): Flow<NetworkResult<Boolean>>
    suspend fun validateEmailAuthentication(emailAuthenticationValidationInfo: EmailAuthenticationValidationInfo): Flow<NetworkResult<Boolean>>
}