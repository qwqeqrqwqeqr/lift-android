package com.gradation.lift.network.datasource

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.APIResult
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
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<APIResult<Token>> = flow {
        networkResultHandler {
            authService.signInDefault(
                signInInfo.toDto()
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(message = result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.toDomain()))
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            authService.signUpDefault(
                signUpInfo.toDto()
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }
}

