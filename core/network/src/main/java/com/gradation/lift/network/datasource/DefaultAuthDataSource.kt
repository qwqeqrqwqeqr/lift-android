package com.gradation.lift.network.datasource

import com.gradation.lift.model.auth.Account
import com.gradation.lift.model.auth.Email
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.DefaultAPIResult
import com.gradation.lift.network.dto.auth.CheckDuplicateEmailRequestDto
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
    override fun signIn(account: Account): Flow<DefaultAPIResult<Token>> = flow {
        networkResultHandler.executeDefault {
            authService.signInDefault(
                SignInDefaultRequestDto(
                    id = account.id,
                    password = account.password
                )
            )
        }.collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.throwable))
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
                is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.throwable))
                is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.result))
            }
        }
    }

    override fun checkDuplicateEmail(email: Email): Flow<DefaultAPIResult<Boolean>> = flow {
        networkResultHandler.executeDefault {
            authService.checkDuplicateEmail(
                CheckDuplicateEmailRequestDto(email = email.email)
            )
        }.collect { result ->
            when (result) {
                is DefaultAPIResult.Fail -> emit(DefaultAPIResult.Fail(result.message))
                is DefaultAPIResult.Error -> emit(DefaultAPIResult.Error(result.throwable))
                is DefaultAPIResult.Success -> emit(DefaultAPIResult.Success(result.data.result))
            }
        }
    }
}

