package com.gradation.lift.network.datasource.auth

import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
) : AuthDataSource {
    override suspend fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            networkResultHandler {
                authService.signInDefault(
                    signInInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.signUpDefault(
                    signUpInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }


    override suspend fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            networkResultHandler {
                authService.signInKakao(
                    signInInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun signUpKakao(signUpInfo: KakaoSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.signUpKakao(
                    signUpInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    override suspend fun signInNaver(signInInfo: NaverSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            networkResultHandler {
                authService.signInNaver(
                    signInInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun signUpNaver(signUpInfo: NaverSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.signUpNaver(
                    signUpInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }


    override suspend fun signInGoogle(signInInfo: GoogleSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            networkResultHandler {
                authService.signInGoogle(
                    signInInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun signUpGoogle(signUpInfo: GoogleSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.signUpGoogle(
                    signUpInfo.toDto()
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }


    override suspend fun checkUserExist(userId: String,email:String): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.checkExistUser(userId,email)
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    override suspend fun updateUserPassword(updatePasswordInfo: UpdatePasswordInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.updateUserPassword(updatePasswordInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    override suspend fun createEmailAuthenticationCode(emailAuthenticationInfo: EmailAuthenticationInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.createEmailAuthenticationCode(emailAuthenticationInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

    override suspend fun validateEmailAuthentication(emailAuthenticationValidationInfo: EmailAuthenticationValidationInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.validateEmailAuthentication(emailAuthenticationValidationInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }

}

