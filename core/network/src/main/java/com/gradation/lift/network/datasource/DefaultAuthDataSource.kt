package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.dto.auth.SignInRequestDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
) : AuthDataSource {
    override suspend fun signIn(account: Account): Flow<APIResult<Token>> = flow {
        networkResultHandler.execute {
            authService.signIn(
                SignInRequestDto(
                    id = account.id,
                    password = account.password
                )
            )
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toToken()))
            }
        }
    }
    override suspend fun refresh(token: Token): Flow<APIResult<Token>> =flow{
        networkResultHandler.execute {
            authService.refresh(refreshToken = token.refreshToken)
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))
                is APIResult.Error -> emit(APIResult.Error(result.exception))
                is APIResult.Loading -> emit(APIResult.Loading)
                is APIResult.Success -> emit(APIResult.Success(result.data.toToken()))
            }
        }
    }


}