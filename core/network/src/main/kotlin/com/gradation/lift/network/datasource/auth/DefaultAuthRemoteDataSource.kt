package com.gradation.lift.network.datasource.auth

import com.gradation.lift.common.common.DispatcherProvider
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
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultAuthRemoteDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : AuthRemoteDataSource {
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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)


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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)


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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)


    override suspend fun checkUserExist(
        userId: String,
        email: String,
    ): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.checkExistUser(userId, email)
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun updateUserPassword(updatePasswordInfo: UpdatePasswordInfo): Flow<NetworkResult<Boolean>> =
        flow {
            networkResultHandler {
                authService.updateUserPassword(updatePasswordInfo.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(
                        NetworkResult.Success(
                            data = result.data.result,
                            message = result.message
                        )
                    )
                }
            }
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)

}

