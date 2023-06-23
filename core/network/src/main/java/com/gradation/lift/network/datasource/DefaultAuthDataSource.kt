package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.dto.auth.SignInRequestDto
import com.gradation.lift.network.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
) : AuthDataSource {
    override suspend fun signIn(account: Account): Flow<DefaultAPIResult<Token>> = flow {
        networkResultHandler.executeDefault {
            authService.signIn(
                SignInRequestDto(
                    id = account.id,
                    password = account.password
                )
            )
        }.collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.exception))
                is DefaultAPIResult.Loading -> emit(DefaultAPIResult.Loading)
                is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.toToken()))
            }
        }
    }
}