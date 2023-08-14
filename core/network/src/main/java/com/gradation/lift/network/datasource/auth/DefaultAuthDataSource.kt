package com.gradation.lift.network.datasource.auth

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val NetworkResultHandler: NetworkResultHandler,
) : AuthDataSource {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> = flow {
        NetworkResultHandler {
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

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> = flow {
        NetworkResultHandler {
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


    override fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            NetworkResultHandler {
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


}

