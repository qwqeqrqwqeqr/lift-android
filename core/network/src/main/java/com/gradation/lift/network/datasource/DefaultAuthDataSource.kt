package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.auth.SignInDefaultRequestDto
import com.gradation.lift.network.dto.auth.SignUpDefaultRequestDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
) : AuthDataSource {
    override fun signIn(signInInfo: SignInInfo): Flow<DefaultAPIResult<Token>> = flow {
        networkResultHandler.executeDefault {
            authService.signInDefault(
                SignInDefaultRequestDto(
                    id = signInInfo.id,
                    password = signInInfo.password
                )
            )
        }.collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(message = result.message,))
                is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.toToken()))
            }
        }
    }

    override fun signUp(signUpInfo: SignUpInfo): Flow<DefaultAPIResult<Boolean>> = flow {
        networkResultHandler.executeDefault {
            authService.signUpDefault(
                SignUpDefaultRequestDto(
                    email = signUpInfo.id,
                    password = signUpInfo.password
                )
            )
        }.collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.result))
            }
        }
    }
}

