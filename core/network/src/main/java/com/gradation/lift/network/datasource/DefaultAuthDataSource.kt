package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
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
    override fun signIn(signInInfo: SignInInfo): Flow<APIResult<Token>> = flow {
        networkResultHandler {
            authService.signInDefault(
                SignInDefaultRequestDto(
                    id = signInInfo.id,
                    password = signInInfo.password
                )
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(message = result.message,))
                is APIResult.Success -> emit(APIResult.Success(result.data.toToken()))
            }
        }
    }

    override fun signUp(signUpInfo: SignUpInfo): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            authService.signUpDefault(
                SignUpDefaultRequestDto(
                    email = signUpInfo.id,
                    password = signUpInfo.password
                )
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }
}

