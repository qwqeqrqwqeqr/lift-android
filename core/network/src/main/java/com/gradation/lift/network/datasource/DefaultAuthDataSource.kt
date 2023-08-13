package com.gradation.lift.network.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.AuthService
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import kotlinx.coroutines.flow.transform


class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val networkResultHandler: NetworkResultHandler,
    @ApplicationContext private val context: Context,
) : AuthDataSource {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> = flow {
        networkResultHandler {
            authService.signInDefault(
                signInInfo.toDto()
            )
        }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> = flow {
        networkResultHandler {
            authService.signUpDefault(
                signUpInfo.toDto()
            )
        }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }


    override fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> = flow {
        networkResultHandler {
            authService.signInKakao(
                signInInfo.toDto()
            )
        }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun signInFromKakao(): Single<NetworkResult<String>> {
        return (if (UserApiClient.instance.isKakaoTalkLoginAvailable(context))
            UserApiClient.rx.loginWithKakaoTalk(context)
        else UserApiClient.rx.loginWithKakaoAccount(context)).zipWith(
            UserApiClient.rx.me()
        ) { _, user ->
            NetworkResult.Success(user.id.toString())
        }
    }
}

